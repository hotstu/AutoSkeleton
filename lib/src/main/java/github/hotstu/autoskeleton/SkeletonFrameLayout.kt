package github.hotstu.autoskeleton

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.content.ContextCompat

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 */
class SkeletonFrameLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val delegate = SkeletonDelegate(this, 2)

    init {
        delegate.setEdgeColor(ContextCompat.getColor(context, R.color.autoskeleton_light_transparent))
        delegate.setShimmerColor(ContextCompat.getColor(context, R.color.autoskeleton_dark_transparent))
        delegate.setEnable(true)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return delegate.onInterceptTouchEvent(ev) || super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return delegate.onTouchEvent(event) || super.onTouchEvent(event)
    }

    override fun dispatchDraw(canvas: Canvas) {
        if (!delegate.dispatchDraw(canvas)) {
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
        if (!delegate.onDraw(canvas)) {
            super.onDraw(canvas)
        }
    }
}