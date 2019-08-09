package com.zwg.adapter.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by longz
 * On 2019/8/7
 * Description 通用listView适配器
 */
abstract class CommonListAdapter<T> : BaseAdapter {
    protected val dataList = ArrayList<T>()
    protected var mContext: Context
    protected var mLayoutRes: Int

    constructor(context: Context, layoutRes: Int) {
        this.mContext = context
        this.mLayoutRes = layoutRes
    }


    /**
     * 设置数据 list
     * @param list
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
     * @param list
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
     * @param list
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var viewHolder: CommonViewListHolder
        if (convertView == null) {
            var itemView = LayoutInflater.from(mContext).inflate(mLayoutRes, parent, false)
            viewHolder = CommonViewListHolder(mContext, itemView, parent, position)
//            onViewHolderCreated(viewHolder, viewHolder.getConvertView())
        } else {
            viewHolder = convertView.tag as CommonViewListHolder
        }
        convert(viewHolder, getItem(position)!!, position)
        return viewHolder.getConvertView()
    }


    override fun getItem(position: Int): T? {
        return if (dataList?.isNotEmpty()) {
            dataList?.get(position) ?: null
        } else {
            null
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return if (dataList?.isNotEmpty()) {
            dataList?.size
        } else {
            0
        }
    }

    abstract fun convert(viewHolder: CommonViewListHolder, t: T?, position: Int)

}