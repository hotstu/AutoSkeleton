package github.hotstu.autoskeleton.demo.easteregg

import android.animation.ValueAnimator
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Shader
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.math.MathUtils
import github.hotstu.autoskeleton.LayeredViewHolder
import github.hotstu.autoskeleton.SkeletonDelegate
import github.hotstu.autoskeleton.demo.R
import pl.droidsonroids.gif.GifDrawable

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc draw circle instead of rect on specific view location
 */
class KunSkeletonDelegate(viewGroup: ViewGroup, drawDepth: Int) : SkeletonDelegate(viewGroup, drawDepth) {
    val kun: GifDrawable by lazy {
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

    override val animator: ValueAnimator
        get() = _animator

    var lastFrame = -1
    override fun updateShader() {
        val layoutWidth = viewGroup.width.toFloat()
        var fl = (animationFraction * kun.numberOfFrames).toInt()
        fl = MathUtils.clamp(fl, 0, kun.numberOfFrames - 1)
        if (fl == lastFrame) {
            return
        }
        lastFrame = fl
        paintMatrix.reset()
        val sx = layoutWidth / kun.intrinsicWidth
        paintMatrix.postScale(sx, sx)
        viewBorderPaint.shader = BitmapShader(
                kun.seekToFrameAndGet(fl),
                Shader.TileMode.CLAMP,
                Shader.TileMode.REPEAT
        ).apply {
            setLocalMatrix(paintMatrix)
        }
    }

    override fun LayeredViewHolder.shouldDrawFake() = this.view is ViewGroup && this.depth == 2

    override fun LayeredViewHolder.drawFake(canvas: Canvas, offsetX: Float, offsetY: Float) {
        with(this.view!!) {
            getLocationInWindow(location)
            viewBoundsRect.set(0, 0, width, height)
            viewBoundsRect.offset(location[0] - offsetX.toInt(), location[1] - offsetY.toInt())
            //canvas.save()
            //canvas.translate(location[0] - offsetX, location[1] - offsetY)

            canvas.drawRect(viewBoundsRect, viewBorderPaint)
            //canvas.restore()
        }

    }


}