package github.hotstu.autoskeleton.demo.databinding

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import github.hotstu.autoskeleton.Skeleton

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc
 */
@BindingAdapter("bind:skeletonEnable")
fun bindSkeletion(view: ViewGroup, enabled: Boolean?) {
    if (enabled == null) {
        return
    }
    view as Skeleton<*>
    if (enabled) {
        view.showSkeleton()
    } else {
        view.hideSkeleton()
    }
}

@BindingAdapter("bind:data")
fun bindSkeletion(view: RecyclerView, data: List<ExampleViewModel.DemoItem>?) {
    if (data == null) {
        return
    }
    with(view.adapter as RealNewsAdapter) {
        setNewDataset(data)
    }
}