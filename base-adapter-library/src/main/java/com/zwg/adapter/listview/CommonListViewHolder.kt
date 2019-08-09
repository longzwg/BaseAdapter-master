package com.zwg.adapter.listview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.util.Linkify
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.*

/**
 * Created by longz
 * On 2019/8/6
 * Description
 */
class CommonViewListHolder {
    private var mViews: SparseArray<View>
    private var mPosition: Int
    private var mConvertView: View
    private var mContext: Context

    constructor(context: Context, itemView: View, parent: ViewGroup?, position: Int) {
        this.mContext = context
        this.mConvertView = itemView
        this.mPosition = position
        this.mViews = SparseArray()
        mConvertView?.tag = this
    }

    fun getConvertView(): View {
        return mConvertView
    }


    /**
     * 拿到一个ViewHolder对象
     */
    fun get(
        context: Context,
        convertView: View,
        parent: ViewGroup,
        layoutId: Int,
        position: Int
    ): CommonViewListHolder {
        if (convertView == null) {
            val itemView = LayoutInflater.from(context).inflate(
                layoutId, parent,
                false
            )
            return CommonViewListHolder(context, itemView, parent, position)
        }

        return convertView.tag as CommonViewListHolder
    }


    /**
     * 通过viewId获取控件
     * @param viewId
     * @return
     */
    fun <T : View> getView(viewId: Int): T {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = mConvertView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }

    /**
     * 更新位置
     * @param position
     */
    fun updatePosition(position: Int) {
        mPosition = position
    }

    /**
     * 获取item 位置
     */
    fun getItemPosition(): Int {
        return mPosition
    }

    /****以下为辅助方法 */

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    fun setText(viewId: Int, text: String): CommonViewListHolder {
        val tv = getView<TextView>(viewId)
        tv.text = text
        return this
    }

    /**
     * 设置ImageView的值
     * @param viewId
     * @param resId
     * @return
     */
    fun setImageResource(viewId: Int, resId: Int): CommonViewListHolder {
        val view = getView<ImageView>(viewId)
        view.setImageResource(resId)
        return this
    }

    /**
     * 设置ImageView的值
     * @param viewId
     * @param bitmap
     * @return
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap): CommonViewListHolder {
        val view = getView<ImageView>(viewId)
        view.setImageBitmap(bitmap)
        return this
    }

    /**
     * 设置ImageView的值
     * @param viewId
     * @param drawable
     * @return
     */
    fun setImageDrawable(viewId: Int, drawable: Drawable): CommonViewListHolder {
        val view = getView<ImageView>(viewId)
        view.setImageDrawable(drawable)
        return this
    }

    /**
     * 设置View的背景
     * @param viewId
     * @param color
     * @return
     */
    fun setBackgroundColor(viewId: Int, color: Int): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this
    }

    /**
     * 设置View的背景
     * @param viewId
     * @param backgroundRes
     * @return
     */
    fun setBackgroundRes(viewId: Int, backgroundRes: Int): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.setBackgroundResource(backgroundRes)
        return this
    }

    /**
     * 设置textView的颜色
     * @param viewId
     * @param textColor
     * @return
     */
    fun setTextColor(viewId: Int, textColor: Int): CommonViewListHolder {
        val view = getView<TextView>(viewId)
        view.setTextColor(textColor)
        return this
    }

    /**
     * 设置textView的颜色
     * @param viewId
     * @param textColorRes
     * @return
     */
    fun setTextColorRes(viewId: Int, textColorRes: Int): CommonViewListHolder {
        val view = getView<TextView>(viewId)
        view.setTextColor(mContext.resources.getColor(textColorRes))
        return this
    }

    /**
     * 设置textView的颜色
     * @param viewId
     * @param textColorRes
     * @return
     */
    fun setTextColorRes(viewId: Int, textColorRes: String): CommonViewListHolder {
        val view = getView<TextView>(viewId)
        view.setTextColor(Color.parseColor(textColorRes))
        return this
    }

    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): CommonViewListHolder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView<View>(viewId).alpha = value
        } else {
            val alpha = AlphaAnimation(value, value)
            alpha.duration = 0
            alpha.fillAfter = true
            getView<View>(viewId).startAnimation(alpha)
        }
        return this
    }

    /**
     * 设置View的显示隐藏
     * @param viewId
     * @param visible
     * @return
     */
    fun setVisible(viewId: Int, visible: Boolean): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    /**
     * 设置超链接下划线
     * @param viewId
     * @return
     */
    fun linkify(viewId: Int): CommonViewListHolder {
        val view = getView<TextView>(viewId)
        Linkify.addLinks(view, Linkify.ALL)
        return this
    }

    /**
     * 设置字体
     * @param typeface
     * @param viewId
     * @return
     */
    fun setTypeface(typeface: Typeface, vararg viewIds: Int): CommonViewListHolder {
        for (viewId in viewIds) {
            val view = getView<TextView>(viewId)
            view.typeface = typeface
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    /**
     * 设置进度
     * @param viewId
     * @param progress
     * @return
     */
    fun setProgress(viewId: Int, progress: Int): CommonViewListHolder {
        val view = getView<ProgressBar>(viewId)
        view.progress = progress
        return this
    }

    /**
     * 设置进度
     * @param viewId
     * @param progress
     * @param max
     * @return
     */
    fun setProgress(viewId: Int, progress: Int, max: Int): CommonViewListHolder {
        val view = getView<ProgressBar>(viewId)
        view.max = max
        view.progress = progress
        return this
    }

    fun setMax(viewId: Int, max: Int): CommonViewListHolder {
        val view = getView<ProgressBar>(viewId)
        view.max = max
        return this
    }

    fun setRating(viewId: Int, rating: Float): CommonViewListHolder {
        val view = getView<RatingBar>(viewId)
        view.rating = rating
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): CommonViewListHolder {
        val view = getView<RatingBar>(viewId)
        view.max = max
        view.rating = rating
        return this
    }

    fun setTag(viewId: Int, tag: Any): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): CommonViewListHolder {
        val view = getView<View>(viewId) as Checkable
        view.isChecked = checked
        return this
    }

    /**
     * 关于事件的
     */
    fun setOnClickListener(
        viewId: Int,
        listener: View.OnClickListener
    ): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(
        viewId: Int,
        listener: View.OnTouchListener
    ): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(
        viewId: Int,
        listener: View.OnLongClickListener
    ): CommonViewListHolder {
        val view = getView<View>(viewId)
        view.setOnLongClickListener(listener)
        return this
    }
}