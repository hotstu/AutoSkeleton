package github.hotstu.autoskeleton.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import github.hotstu.autoskeleton.SkeletonFrameLayout
import github.hotstu.autoskeleton.demo.adapter.NewsAdapter
import github.hotstu.autoskeleton.demo.adapter.PersonAdapter



class RecyclerViewActivity : AppCompatActivity() {
    private var mType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        mType = intent.getStringExtra(PARAMS_TYPE)
        init()
    }


    private fun init() {
        val root = findViewById<SkeletonFrameLayout>(R.id.rootView)
        root.postDelayed({ root.hideSkeleton() }, 3000)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        if (TYPE_LINEAR == mType) {
            recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
            val adapter = NewsAdapter()
            recyclerView.adapter = adapter
            return
        }
        if (TYPE_GRID == mType) {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
            val adapter = PersonAdapter()
            recyclerView.adapter = adapter
        }
    }

    companion object {
        private val PARAMS_TYPE = "params_type"
        val TYPE_LINEAR = "type_linear"
        val TYPE_GRID = "type_grid"

        fun start(context: Context, type: String) {
            val intent = Intent(context, RecyclerViewActivity::class.java)
            intent.putExtra(PARAMS_TYPE, type)
            context.startActivity(intent)
        }
    }

}

