package github.hotstu.autoskeleton.demo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_list).setOnClickListener { RecyclerViewActivity.start(this@MainActivity, RecyclerViewActivity.TYPE_LINEAR) }
        findViewById<View>(R.id.btn_grid).setOnClickListener { RecyclerViewActivity.start(this@MainActivity, RecyclerViewActivity.TYPE_GRID) }
        findViewById<View>(R.id.btn_view).setOnClickListener { FullViewActivity.start(this@MainActivity, FullViewActivity.TYPE_VIEW) }
    }
}
