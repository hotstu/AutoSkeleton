package github.hotstu.autoskeleton.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import github.hotstu.autoskeleton.demo.adapter.TopicAdapter;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class ViewActivity extends AppCompatActivity {


    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_IMG_LOADING = "type_img";
    public static final String TYPE_VIEW = "type_view";

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, ViewActivity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        String mType = getIntent().getStringExtra(PARAMS_TYPE);
        View rootView = findViewById(R.id.rootView);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final TopicAdapter adapter = new TopicAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(adapter);

            }
        });
    }


}
