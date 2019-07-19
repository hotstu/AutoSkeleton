package github.hotstu.autoskeleton

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 */
class SkeletonFrameLayout : FrameLayout, AnimatedSkeleton<FrameLayout> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val delegate = SkeletonDelegate(this, 2)

    init {
        delegate.setEdgeColor(ContextCompat.getColor(context, R.color.autoskeleton_light_transparent))
        delegate.setShimmerColor(ContextCompat.getColor(context, R.color.autoskeleton_dark_transparent))
        delegate.enabled = true
    }

    override fun hideSkeleton() {
        delegate.stopAnimate()
        delegate.enabled = false
    }

    override fun showSkeleton() {
        delegate.enabled = true
        delegate.startAnimate()
    }


    override fun dispatchDraw(canvas: Canvas) {
        if (!delegate.enabled) {
            super.dispatchDraw(canvas)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        delegate.startAnimate()
    }

    override fun onDetachedFromWindow() {
        delegate.stopAnimate()
        super.onDetachedFromWindow()
    }

    override fun onDraw(canvas: Canvas) {
        if (!delegate.enabled) {
            super.onDraw(canvas)
        } else {
            delegate.onDraw(canvas)
        }
    }
}