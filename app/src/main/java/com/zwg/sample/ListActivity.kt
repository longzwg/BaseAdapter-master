package com.zwg.sample

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zwg.adapter.listview.CommonListAdapter
import com.zwg.adapter.listview.CommonViewListHolder
import com.zwg.adapter.recyclerview.CommonRecyclerAdapter
import com.zwg.adapter.recyclerview.CommonRecyclerViewHolder
import kotlinx.android.synthetic.main.activity_list.*
import java.io.Serializable

/**
 * Created by longz
 * On 2019/8/8
 * Description
 */
class ListActivity : AppCompatActivity() {
    private var dataList: List<DataBean>? = null
    private var dataArray: Array<DataBean>? = null
    private var dataArrayList = ArrayList<DataBean>()
    private var listAdapter: CommonListAdapter<DataBean>? = null
    private var recyclerAdapter: CommonRecyclerAdapter<DataBean>? = null
    private var type: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        var bundle = intent.getBundleExtra("data")
        bundle?.let {
            type = bundle.getString("type")
        }
        initData()
        initAdapter(type ?: "")
    }

    private fun initAdapter(type: String) {
        when (type) {
            "list", "array", "arrayList" -> {
                listView?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
                listAdapter = object : CommonListAdapter<DataBean>(this, R.layout.item_lv) {

                    override fun convert(viewHolder: CommonViewListHolder, t: DataBean?, position: Int) {
                        viewHolder.setText(R.id.name, t?.name ?: "")
                        viewHolder.setText(R.id.content, t?.content ?: "")
                        viewHolder.setOnClickListener(R.id.name,
                            View.OnClickListener {
                                Toast.makeText(this@ListActivity, t?.name ?: "", Toast.LENGTH_SHORT).show()
                            })
                    }
                }
                listView?.adapter = listAdapter
            }
            "recycleList", "recycleArray", "recycleArrayList" -> {
                listView?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
                recyclerAdapter = object : CommonRecyclerAdapter<DataBean>(this) {
                    override fun convert(viewHolder: CommonRecyclerViewHolder, t: DataBean?, position: Int) {
                        viewHolder.setText(R.id.name, t?.name ?: "")
                        viewHolder.setText(R.id.content, t?.content ?: "")
                        viewHolder.setOnClickListener(R.id.name, object : View.OnClickListener {
                            override fun onClick(p0: View?) {
                                Toast.makeText(this@ListActivity, t?.name ?: "", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }

                    override fun getLayoutRes(viewType: Int): Int {
                        return R.layout.item_lv
                    }
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                recyclerView?.layoutManager = layoutManager
                //防止item位置互换
                layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE)
                recyclerView?.adapter = recyclerAdapter
            }
        }
        when (type) {
            "list" -> {
                listAdapter?.setDataList(dataList)

            }
            "array" -> {
                listAdapter?.setDataList(dataArray)
            }
            "arrayList" -> {
                listAdapter?.setDataList(dataArrayList)
            }
            "recycleList" -> {
                recyclerAdapter?.setDataList(dataList)
            }
            "recycleArray" -> {
                recyclerAdapter?.setDataList(dataArray)
            }
            "recycleArrayList" -> {
                recyclerAdapter?.setDataList(dataArrayList)
                Handler().postDelayed(Runnable {
                    for (i in 21..40) {
                        dataArrayList.add(DataBean("name:${i}", "content:${i}"))
                    }
                    recyclerAdapter?.setDataList(dataArrayList)
                }, 5000)
            }
        }

    }

    private fun initData() {
        dataList = listOf(
            DataBean("name1", "content1"),
            DataBean("name2", "content2"),
            DataBean("name3", "content3"),
            DataBean("name4", "content4"),
            DataBean("name5", "content5"),
            DataBean("name6", "content6"),
            DataBean("name7", "content7"),
            DataBean("name8", "content8"),
            DataBean("name9", "content9"),
            DataBean("name10", "content10"),
            DataBean("name11", "content11"),
            DataBean("name12", "content12"),
            DataBean("name13", "content13"),
            DataBean("name14", "content14")
        )
        dataArray = arrayOf(
            DataBean("name-1", "content-1"),
            DataBean("name-2", "content-2"),
            DataBean("name-3", "content-3"),
            DataBean("name-4", "content-4"),
            DataBean("name-5", "content-5"),
            DataBean("name-6", "content-6"),
            DataBean("name-7", "content-7"),
            DataBean("name-8", "content-8"),
            DataBean("name-9", "content-9"),
            DataBean("name-10", "content-10"),
            DataBean("name-11", "content-11"),
            DataBean("name-12", "content-12"),
            DataBean("name-13", "content-13"),
            DataBean("name-14", "content-14"),
            DataBean("name-15", "content-15")
        )
        for (i in 0..20) {
            dataArrayList.add(DataBean("name:${i}", "content:${i}"))
        }

    }
}

class DataBean : Serializable {
    var name: String? = null
    var content: String? = null

    constructor(name: String, content: String) {
        this.name = name
        this.content = content
    }

}