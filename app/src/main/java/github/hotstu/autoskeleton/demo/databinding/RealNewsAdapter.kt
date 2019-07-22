package github.hotstu.autoskeleton.demo.databinding

import github.hotstu.autoskeleton.demo.adapter.NewsAdapter

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc
 */
class RealNewsAdapter : NewsAdapter() {
    var dataset: List<ExampleViewModel.DemoItem> = UNSET


    override fun getItemCount(): Int {
        if (dataset == UNSET) {
            return 9
        } else {
            return dataset.size
        }
    }

    fun setNewDataset(data: List<ExampleViewModel.DemoItem>) {
        if (dataset == UNSET) {
            dataset = ArrayList(data)
        }
        notifyDataSetChanged()
    }

    companion object {
        val UNSET: List<ExampleViewModel.DemoItem> = listOf()
    }
}