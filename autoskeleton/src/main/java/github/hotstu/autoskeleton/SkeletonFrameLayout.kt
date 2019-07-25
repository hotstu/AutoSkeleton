package github.hotstu.autoskeleton

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 */
class SkeletonFrameLayout : FrameLayout, AnimatedSkeleton<FrameLayout> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var delegate = SkeletonDelegate(this, 3)

    init {
        delegate.enabled = true

    }

    fun setSkeletonDelegate(delegate: SkeletonDelegate) {
        hideSkeleton()
        this.delegate = delegate
    }

    override fun hideSkeleton() {
        delegate.stopAnimate()
        delegate.enabled = false
        isClickable = false
    }

    override fun showSkeleton() {
        delegate.enabled = true
        delegate.startAnimate()
        isClickable = true
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