package com.zwg.adapter.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by longz
 * On 2019/8/7
 * Description
 */
abstract class CommonRecyclerAdapter<T> : RecyclerView.Adapter<CommonRecyclerViewHolder> {
    protected val dataList = ArrayList<T>()
    protected var mContext: Context

    constructor(context: Context) {
        this.mContext = context
    }


    /**
     * 设置数据 list
     */
    fun setDataList(list: List<T>?) {
        dataList.clear()
        list?.let {
            this.dataList.addAll(it)
        }
        notifyDataSetChanged()
    }

    /**
     * 设置数据 list
     */
    fun setDataList(list: ArrayList<T>?) {
        dataList.clear()
        list?.let {
            this.dataList.addAll(it)
        }
        notifyDataSetChanged()
    }

    /**
     * 设置数据 array
     */
    fun setDataList(list: Array<T>?) {
        list?.let {
            var arrayList = ArrayList<T>()
            for (i in it.indices) {
                arrayList.add(it[i])
            }
            setDataList(arrayList)
        }
    }


    override fun onBindViewHolder(holder: CommonRecyclerViewHolder, position: Int) {
        convert(holder, dataList[position], position)
    }

    override fun getItemCount(): Int {
        return if (dataList?.isNotEmpty()) {
            dataList?.size
        } else {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonRecyclerViewHolder {
        return CommonRecyclerViewHolder(
            mContext,
            LayoutInflater.from(mContext).inflate(getLayoutRes(viewType), parent, false)
        )
    }


    abstract fun convert(viewHolder: CommonRecyclerViewHolder, t: T?, position: Int)
    abstract fun getLayoutRes(viewType: Int): Int
}