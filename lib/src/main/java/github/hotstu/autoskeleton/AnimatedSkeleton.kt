package github.hotstu.autoskeleton

import android.view.ViewGroup

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/19/19
 * @desc
 */
interface Skeleton<T : ViewGroup> {
    fun hideSkeleton()
    fun showSkeleton()
}

interface AnimatedSkeleton<T : ViewGroup> : Skeleton<T> {
}