package github.hotstu.autoskeleton.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import github.hotstu.autoskeleton.demo.custom.CustomFullViewActivity
import github.hotstu.autoskeleton.demo.databinding.ExampleActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.btn_list).setOnClickListener { RecyclerViewActivity.start(this@MainActivity, RecyclerViewActivity.TYPE_LINEAR) }
        findViewById<View>(R.id.btn_grid).setOnClickListener { RecyclerViewActivity.start(this@MainActivity, RecyclerViewActivity.TYPE_GRID) }
        findViewById<View>(R.id.btn_view).setOnClickListener { FullViewActivity.start(this@MainActivity, FullViewActivity.TYPE_VIEW) }
        findViewById<View>(R.id.btn_databinding).setOnClickListener { startActivity(Intent(this, ExampleActivity::class.java)) }
        findViewById<View>(R.id.btn_custom).setOnClickListener { startActivity(Intent(this, CustomFullViewActivity::class.java)) }
    }
}
