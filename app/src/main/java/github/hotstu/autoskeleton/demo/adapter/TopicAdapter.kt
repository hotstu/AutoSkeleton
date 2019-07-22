package github.hotstu.autoskeleton.demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import github.hotstu.autoskeleton.demo.R



class TopicAdapter : RecyclerView.Adapter<SimpleRcvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleRcvViewHolder {
        return if (viewType == TYPE_HEADER) {
            SimpleRcvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_title_more, parent, false))
        } else SimpleRcvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false))
    }

    override fun onBindViewHolder(holder: SimpleRcvViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else TYPE_CONTENT
    }

    companion object {
        private val TYPE_HEADER = 1
        private val TYPE_CONTENT = 2
    }
}
