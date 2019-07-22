package github.hotstu.autoskeleton

import android.animation.ValueAnimator
import android.graphics.*
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.util.Pools
import androidx.core.view.children
import androidx.core.view.doOnPreDraw
import java.util.*

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 * @param viewGroup
 * @param drawDepth
 */
open class SkeletonDelegate(val viewGroup: ViewGroup, val drawDepth: Int = 2) {
    companion object {
        const val DEFAULT_SHIMMER_DURATION = 1_500L
    }

    val location = IntArray(2)
    val viewBoundsRect = Rect()
    val layeredViewQueue = ArrayDeque<LayeredViewHolder>()
    val viewBorderPaint = Paint().apply {
        style = Paint.Style.FILL
        isDither = true
        isAntiAlias = true
    }

    private val _animator: ValueAnimator by lazy {
        ValueAnimator.ofFloat(0f, 1f)
                .apply {
                    repeatCount = ValueAnimator.INFINITE
                    duration = DEFAULT_SHIMMER_DURATION
                    interpolator = AnticipateInterpolator()
                }
    }

    open val animator: ValueAnimator
        get() = _animator

    val layeredViewPool by lazy {
        ViewPool(10) {
            LayeredViewHolder()
        }
    }
    val paintMatrix = Matrix()
    var enabled = false
        set(value) {
            field = value
            viewGroup.setWillNotDraw(!enabled)
            viewGroup.postInvalidate()
        }
    var animationFraction = 0f
    private var mEdgeColor = Color.WHITE
    private var mShimmerColor = Color.GRAY
    private var mShimmerDuration = DEFAULT_SHIMMER_DURATION

    init {
        mEdgeColor = ContextCompat.getColor(viewGroup.context, R.color.autoskeleton_light_transparent)
        mShimmerColor = ContextCompat.getColor(viewGroup.context, R.color.autoskeleton_dark_transparent)
    }

    fun setEdgeColor(@ColorInt color: Int) {
        mEdgeColor = color
    }

    fun setShimmerColor(@ColorInt color: Int) {
        mShimmerColor = color
    }

    fun setShimmerDuration(duration: Long) {
        mShimmerDuration = duration
    }


    fun startAnimate() {
        if (enabled && !animator.isStarted) {
            viewGroup.doOnPreDraw {
                animator.addUpdateListener {
                    animationFraction = it.animatedFraction
                    viewGroup.postInvalidate()
                }
                animator.start()
            }
            viewGroup.postInvalidate()
        }
    }


    fun stopAnimate() {
        animator.end()
        animator.removeAllUpdateListeners()
    }


    /**
     * by override this funciton you define your own shader pattern
     */
    open fun updateShader() {
        val layoutWidth = viewGroup.width.toFloat()
        val fl = (animationFraction - .5f) * layoutWidth * 1.5f
        paintMatrix.reset()
        paintMatrix.postTranslate(fl, 0f)
        viewBorderPaint.shader = LinearGradient(
                0f, 0f,
                layoutWidth, 0f,
                intArrayOf(
                        mEdgeColor, mShimmerColor, mEdgeColor
                ),
                floatArrayOf(
                        .25f, .5f, .65f
                ),
                Shader.TileMode.CLAMP
        ).apply {
            setLocalMatrix(paintMatrix)
        }
    }

    /**
     * by override this function you can filter which type of view will be drawn
     */
    open fun LayeredViewHolder.shouldDrawFake() = this.view !is ViewGroup

    /**
     * by override this function you can make your own drawing
     */
    open fun LayeredViewHolder.drawFake(canvas: Canvas, offsetX: Float, offsetY: Float) {
        with(this.view!!) {
            getLocationInWindow(location)
            viewBoundsRect.set(0, 0, width, height)
            viewBoundsRect.offset(location[0] - offsetX.toInt(), location[1] - offsetY.toInt())
            canvas.drawRect(viewBoundsRect, viewBorderPaint)
        }

    }

    open fun onDraw(canvas: Canvas) {
        with(viewGroup) {
            getLocationInWindow(location)
            val offsetX = location[0].toFloat()
            val offsetY = location[1].toFloat()
            if (!layeredViewQueue.isEmpty()) {
                throw AssertionError("View queue is not empty.")
            }
            layeredViewQueue.add(
                    layeredViewPool.acquire()
                            .apply {
                                set(viewGroup, 0)
                            }
            )

            updateShader()
            while (!layeredViewQueue.isEmpty()) {
                val layeredView = layeredViewQueue.removeFirst()
                val view = layeredView.view
                val layer = layeredView.depth
                if (layer > drawDepth) {
                    layeredViewQueue.clear()
                    break
                }

                if (layeredView.shouldDrawFake()) {
                    layeredView.drawFake(canvas, offsetX, offsetY)
                }
                // Restore the object to the pool for use later.
                layeredView.clear()
                layeredViewPool.release(layeredView)
                with(view!!) {
                    //queue children for later drawing.
                    if (this is ViewGroup && layer < drawDepth) {
                        layeredViewQueue.addAll(
                                children
                                        .filter { it.visibility == View.VISIBLE }
                                        .map { view ->
                                            layeredViewPool.acquire().apply {
                                                set(view, layer + 1)
                                            }
                                        }
                        )
                    }
                }
            }
        }

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