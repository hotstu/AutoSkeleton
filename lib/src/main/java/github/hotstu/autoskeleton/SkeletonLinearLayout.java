package github.hotstu.autoskeleton;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

/**
 * this view demonstrate how to implement you own ViewGroup by simply override #dispatchDraw and #onDraw
 *
 * @author hglf [hglf](https://github.com/hotstu)
 * @desc
 * @since 7/19/19
 */
public class SkeletonLinearLayout extends LinearLayout implements AnimatedSkeleton {
    private SkeletonDelegate delegate;

    public SkeletonLinearLayout(Context context) {
        super(context);
        init();
    }

    public SkeletonLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SkeletonLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        delegate = new SkeletonDelegate(this, 2);
        delegate.setEdgeColor(ContextCompat.getColor(getContext(), R.color.autoskeleton_light_transparent));
        delegate.setShimmerColor(ContextCompat.getColor(getContext(), R.color.autoskeleton_dark_transparent));
        delegate.setEnabled(true);
    }

    @Override
    public void hideSkeleton() {
        delegate.stopAnimate();
        delegate.setEnabled(false);
    }

    @Override
    public void showSkeleton() {
        delegate.setEnabled(true);
        delegate.startAnimate();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return delegate.onInterceptTouchEvent(ev) || super.onInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return delegate.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        if (!delegate.getEnabled()) {
            super.dispatchDraw(canvas);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (!delegate.getEnabled()) {
            super.onDraw(canvas);
        } else {
            delegate.onDraw(canvas);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        delegate.startAnimate();

    }

    @Override
    public void onDetachedFromWindow() {
        delegate.stopAnimate();
        super.onDetachedFromWindow();
    }


}
