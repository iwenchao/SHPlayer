package cn.iwenchaos.shplayer.module.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.iwenchaos.shplayer.R
import cn.iwenchaos.shplayer.base.BaseActivity
import cn.iwenchaos.shplayer.databinding.ActivityMainBinding
import cn.iwenchaos.shplayer.module.main.vm.MainViewModel
import kotlinx.android.synthetic.main.main_recycler_list_item.view.*

class MainActivity : BaseActivity() {


    var viewDataBinding: ActivityMainBinding? = null
    var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewDataBinding?.recyclerView?.run {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = ItemAdapter(mainViewModel?.getEntranceList())
        }


    }


    class ItemAdapter(private val items: MutableList<String>?) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_recycler_list_item, parent, false)
            return ItemViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return if (items.isNullOrEmpty()) 0 else items.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.tvItem.text = items?.get(position)
        }

    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem: TextView?
            get() = itemView.tvItem


    }


}
