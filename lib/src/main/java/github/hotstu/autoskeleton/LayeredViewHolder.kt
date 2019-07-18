package github.hotstu.autoskeleton

import android.view.View

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/18/19
 * @desc
 */
class LayeredViewHolder(var view: View? = null, var depth: Int = 0) {
    fun set(v: View, depth: Int) {
        view = v
        this.depth = depth
    }

    fun clear() {
        view = null
        depth = 0
    }
}