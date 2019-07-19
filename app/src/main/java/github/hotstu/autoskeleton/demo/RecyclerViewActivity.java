package github.hotstu.autoskeleton.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import github.hotstu.autoskeleton.SkeletonFrameLayout;
import github.hotstu.autoskeleton.demo.adapter.NewsAdapter;
import github.hotstu.autoskeleton.demo.adapter.PersonAdapter;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;


/**
 * Created by ethanhua on 2017/7/27.
 */

public class RecyclerViewActivity extends AppCompatActivity {


    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_LINEAR = "type_linear";
    public static final String TYPE_GRID = "type_grid";
    private String mType;

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mType = getIntent().getStringExtra(PARAMS_TYPE);
        init();
    }


    private void init() {
        final SkeletonFrameLayout root = findViewById(R.id.rootView);
        root.postDelayed(new Runnable() {
            @Override
            public void run() {
                root.hideSkeleton();
            }
        }, 3000);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (TYPE_LINEAR.equals(mType)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
            NewsAdapter adapter = new NewsAdapter();
            recyclerView.setAdapter(adapter);
            return;
        }
        if (TYPE_GRID.equals(mType)) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            PersonAdapter adapter = new PersonAdapter();
            recyclerView.setAdapter(adapter);
        }
    }

}

