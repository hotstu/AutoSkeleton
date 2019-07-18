package github.hotstu.autoskeleton

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pools
import androidx.core.view.children
import java.util.*

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 * @param viewGroup
 * @param drawDepth
 */
open class SkeletonDelegate(private val viewGroup: ViewGroup, val drawDepth: Int) {
    private var enabled = false
    private val location = IntArray(2)
    private val viewBoundsRect = Rect()
    private val viewBorderPaint = Paint().apply {
        style = Paint.Style.FILL
        shader = LinearGradient(0f, 0f, 100f,100f, Color.WHITE, Color.BLACK, Shader.TileMode.REPEAT)
    }

    private val layeredViewQueue = ArrayDeque<LayeredViewHolder>()
    val layeredViewPool = ViewPool(10) {
        LayeredViewHolder()
    }

    fun setEnable(v: Boolean) {
        enabled = v
        viewGroup.setWillNotDraw(!enabled)
        viewGroup.postInvalidate()
    }

    fun onInterceptTouchEvent(ev: MotionEvent): Boolean = false

    fun onTouchEvent(event: MotionEvent): Boolean = false

    fun dispatchDraw(canvas: Canvas): Boolean = enabled

    /**
     * by override this function you can filter which type of view will be drawn
     */
    open fun shouldDraw(v: View) = v !is ViewGroup

    //TODO support shader drawing & animate by set the paint
    fun onDraw(canvas: Canvas): Boolean {
        with(viewGroup) {
            getLocationInWindow(location)
            val x = location[0].toFloat()
            val y = location[1].toFloat()
            val saveCount = canvas.save()
            if (!layeredViewQueue.isEmpty()) {
                throw AssertionError("View queue is not empty.")
            }
            layeredViewQueue.addAll(
                    children
                            .filter { it.visibility == View.VISIBLE }
                            .mapIndexed { _, view ->
                                layeredViewPool.acquire()
                                        .apply {
                                            set(view, 0)
                                        }
                            }
            )

            while (!layeredViewQueue.isEmpty()) {
                val layeredView = layeredViewQueue.removeFirst()
                val view = layeredView.view
                val layer = layeredView.depth
                if (layer > drawDepth) {
                    layeredViewQueue.clear()
                    break
                }
                // Restore the object to the pool for use later.
                layeredView.clear()
                layeredViewPool.release(layeredView)

                view?.run {
                    if (shouldDraw(this)) {
                        val viewSaveCount = canvas.save()
                        getLocationInWindow(location)
                        canvas.translate(location[0] - x, location[1] - y)
                        viewBoundsRect.set(0, 0, width, height)
                        canvas.drawRect(viewBoundsRect, viewBorderPaint)
                        canvas.restoreToCount(viewSaveCount)
                    }

                    //queue children for later drawing.
                    if (this is ViewGroup && layer < drawDepth) {
                        layeredViewQueue.addAll(
                                children
                                        .filter { it.visibility == View.VISIBLE }
                                        .mapIndexed { _, view ->
                                            layeredViewPool.acquire().apply {
                                                set(view, layer + 1)
                                            }
                                        }
                        )
                    }
                }

            }
            canvas.restoreToCount(saveCount)
        }
        return enabled

    }
}


/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 */
class ViewPool<T>(private val initialSize: Int = 10, val factory: () -> T) : Pools.Pool<T> {
    private val pool: Deque<T>

    init {
        pool = ArrayDeque()
        pool.addAll((0 until initialSize).map {
            factory()
        })
    }

    override fun acquire(): T {
        return if (pool.isEmpty()) {
            factory()
        } else {
            pool.removeLast()
        }
    }

    //the capacity of the pool increase with the count of child of this view
    override fun release(instance: T): Boolean {
        pool.addLast(instance)
        return true
    }
}