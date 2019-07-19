package github.hotstu.autoskeleton.demo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import github.hotstu.autoskeleton.demo.R;


/**
 * Created by ethanhua on 2017/7/27.
 */

public class NewsAdapter extends RecyclerView.Adapter<SimpleRcvViewHolder> {


    @Override
    public SimpleRcvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleRcvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleRcvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


}
