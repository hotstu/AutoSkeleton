package github.hotstu.autoskeleton.demo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import github.hotstu.autoskeleton.demo.R;


/**
 * Created by ethanhua on 2017/7/29.
 */

public class TopicAdapter extends RecyclerView.Adapter<SimpleRcvViewHolder> {
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_CONTENT = 2;

    @NotNull
    @Override
    public SimpleRcvViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new SimpleRcvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_more, parent, false));
        }
        return new SimpleRcvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull SimpleRcvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_CONTENT;
    }
}
