package github.hotstu.autoskeleton.demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import github.hotstu.autoskeleton.Skeleton
import github.hotstu.autoskeleton.demo.adapter.TopicAdapter

class FullViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val s = findViewById<View>(R.id.skeleton) as Skeleton<*>
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = TopicAdapter()
        val linearLayoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.post { recyclerView.adapter = adapter }
        recyclerView.postDelayed({ s.hideSkeleton() }, 3000)
    }

    companion object {

        private val PARAMS_TYPE = "params_type"
        val TYPE_VIEW = "type_view"

        fun start(context: Context, type: String) {
            val intent = Intent(context, FullViewActivity::class.java)
            intent.putExtra(PARAMS_TYPE, type)
            context.startActivity(intent)
        }
    }


}
