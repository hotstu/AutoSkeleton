package github.hotstu.autoskeleton.demo.custom

import android.graphics.Canvas
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.toRectF
import github.hotstu.autoskeleton.LayeredViewHolder
import github.hotstu.autoskeleton.SkeletonDelegate

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc
 */
class CustomSkeletonDelegate(viewGroup: ViewGroup, drawDepth: Int) : SkeletonDelegate(viewGroup, drawDepth) {

    override fun LayeredViewHolder.drawFake(canvas: Canvas, offsetX: Float, offsetY: Float) {
        with(this.view!!) {
            getLocationInWindow(location)
            viewBoundsRect.set(0, 0, width, height)
            viewBoundsRect.offset(location[0] - offsetX.toInt(), location[1] - offsetY.toInt())
            if (viewBoundsRect.width() == viewBoundsRect.height() && this is ImageView) {
                canvas.drawOval(viewBoundsRect.toRectF(), viewBorderPaint)
            } else {
                canvas.drawRect(viewBoundsRect, viewBorderPaint)
            }
        }

    }


}