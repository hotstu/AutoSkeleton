package github.hotstu.autoskeleton.demo.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import github.hotstu.autoskeleton.demo.BR
import github.hotstu.autoskeleton.demo.R

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @since 7/22/19
 * @desc a real world demo using android dataBinding & viewModel
 */
class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewDataBinding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_recyclerview)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = RealNewsAdapter()

        val viewModel = ViewModelProviders.of(this).get(ExampleViewModel::class.java)
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.lifecycleOwner =  this

    }
}