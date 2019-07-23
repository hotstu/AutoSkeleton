package github.hotstu.autoskeleton.demo.easteregg

import android.animation.ValueAnimator
import android.graphics.*
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.math.MathUtils
import github.hotstu.autoskeleton.LayeredViewHolder
import github.hotstu.autoskeleton.SkeletonDelegate
import github.hotstu.autoskeleton.demo.R
import pl.droidsonroids.gif.GifDrawable
import kotlin.math.roundToInt


/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc draw circle instead of rect on specific view location
 */
class KunSkeletonDelegate(viewGroup: ViewGroup, drawDepth: Int) : SkeletonDelegate(viewGroup, drawDepth) {
    private val kun: GifDrawable by lazy {
        GifDrawable.createFromResource(viewGroup.resources, R.drawable.kun)!!.apply {
            stop()
        }
    }

    private val _animator: ValueAnimator by lazy {
        ValueAnimator.ofFloat(0f, 1f)
                .apply {
                    repeatCount = ValueAnimator.INFINITE
                    duration = 3_500L
                    interpolator = LinearInterpolator()
                }
    }

    init {
        viewGroup.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override val animator: ValueAnimator
        get() = _animator

    var lastFrame = -1
    override fun updateShader() {
        val layoutWidth = viewGroup.width.toFloat()
        val layoutHeight = viewGroup.height.toFloat()
        var fl = (animationFraction * kun.numberOfFrames).toInt()
        fl = MathUtils.clamp(fl, 0, kun.numberOfFrames - 1)
        if (fl == lastFrame) {
            return
        }
        lastFrame = fl
        paintMatrix.reset()

        val sx = layoutWidth / kun.intrinsicWidth
        val sy = layoutHeight / kun.intrinsicHeight

        paintMatrix.postTranslate(layoutWidth /2 -kun.intrinsicWidth/2, layoutHeight/2 - kun.intrinsicHeight/2)
        paintMatrix.postScale(sy, sy, layoutWidth/2, layoutHeight/2)
        viewBorderPaint.shader = BitmapShader(
                makeTransparentEdgeBitmap(kun.seekToFrameAndGet(fl)),
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
        ).apply {
            setLocalMatrix(paintMatrix)
        }
    }


    val localPaint = Paint()

    //unfortunately the gif does not have transparent edge which makes the CLAMP drawing broken
    private fun makeTransparentEdgeBitmap(bitmap: Bitmap): Bitmap {
       return Bitmap.createBitmap(bitmap.width + 2, bitmap.height + 2, Bitmap.Config.ARGB_8888).apply {
            Canvas(this).drawBitmap(bitmap,1f,1f, localPaint)
        }

    }

    override fun LayeredViewHolder.shouldDrawFake() = this.view is ViewGroup && this.depth == 2

    override fun LayeredViewHolder.drawFake(canvas: Canvas, offsetX: Float, offsetY: Float) {
        with(this.view!!) {
            getLocationInWindow(location)
            viewBoundsRect.set(0, 0, width, height)
            viewBoundsRect.offset(location[0] - offsetX.roundToInt(), location[1] - offsetY.roundToInt())
            //canvas.save()
            //canvas.translate(location[0] - offsetX, location[1] - offsetY)

            canvas.drawRect(viewBoundsRect, viewBorderPaint)
            //canvas.restore()
        }

    }


}