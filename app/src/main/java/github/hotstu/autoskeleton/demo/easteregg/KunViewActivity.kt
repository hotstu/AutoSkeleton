package github.hotstu.autoskeleton.demo.easteregg

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import github.hotstu.autoskeleton.SkeletonLinearLayout
import github.hotstu.autoskeleton.demo.R
import github.hotstu.autoskeleton.demo.adapter.TopicAdapter

class KunViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val s = findViewById<View>(R.id.skeleton) as SkeletonLinearLayout
        s.setDelegate(KunSkeletonDelegate(s, 0))
        s.showSkeleton()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TopicAdapter()
        val linearLayoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.post { recyclerView.adapter = adapter }
       // recyclerView.postDelayed({ s.hideSkeleton() }, 3000)
    }

}
