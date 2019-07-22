package github.hotstu.autoskeleton.demo.adapter

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SimpleRcvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val views = SparseArray<View>()

    fun <V : View> getView(resId: Int): V {
        var v: View? = views.get(resId)
        if (null == v) {
            v = itemView.findViewById(resId)
            if (v == null) {
                throw RuntimeException("can fild $resId")
            }
            views.put(resId, v)
        }
        return v as V
    }
}