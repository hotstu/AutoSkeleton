package github.hotstu.autoskeleton.demo.easteregg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import github.hotstu.autoskeleton.SkeletonFrameLayout
import github.hotstu.autoskeleton.demo.R
import github.hotstu.autoskeleton.demo.adapter.NewsAdapter

class KunViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
            val adapter = NewsAdapter()
            recyclerView.adapter = adapter

        val root = findViewById<SkeletonFrameLayout>(R.id.rootView)
        root.setSkeletonDelegate(KunSkeletonDelegate(root, 2))
        root.showSkeleton()
       recyclerView.postDelayed({ root.hideSkeleton() }, 3000)
    }

}
