package com.zwg.adapter.recyclerview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.util.Linkify
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by longz
 * On 2019/8/6
 * Description
 */
class CommonRecyclerViewHolder : RecyclerView.ViewHolder {
    private var mViews: SparseArray<View>
    protected var mConvertView: View
    private var mContext: Context

    constructor(context: Context, itemView: View) : super(itemView) {
        this.mContext = context
        this.mConvertView = itemView
        this.mViews = SparseArray()
    }

    fun getConvertView(): View {
        return mConvertView
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


    /****以下为辅助方法 */

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    fun setText(viewId: Int, text: String): CommonRecyclerViewHolder {
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
    fun setImageResource(viewId: Int, resId: Int): CommonRecyclerViewHolder {
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
    fun setImageBitmap(viewId: Int, bitmap: Bitmap): CommonRecyclerViewHolder {
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
    fun setImageDrawable(viewId: Int, drawable: Drawable): CommonRecyclerViewHolder {
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
    fun setBackgroundColor(viewId: Int, color: Int): CommonRecyclerViewHolder {
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
    fun setBackgroundRes(viewId: Int, backgroundRes: Int): CommonRecyclerViewHolder {
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
    fun setTextColor(viewId: Int, textColor: Int): CommonRecyclerViewHolder {
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
    fun setTextColorRes(viewId: Int, textColorRes: Int): CommonRecyclerViewHolder {
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
    fun setTextColorRes(viewId: Int, textColorRes: String): CommonRecyclerViewHolder {
        val view = getView<TextView>(viewId)
        view.setTextColor(Color.parseColor(textColorRes))
        return this
    }

    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): CommonRecyclerViewHolder {
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
    fun setVisible(viewId: Int, visible: Boolean): CommonRecyclerViewHolder {
        val view = getView<View>(viewId)
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    /**
     * 设置超链接下划线
     * @param viewId
     * @return
     */
    fun linkify(viewId: Int): CommonRecyclerViewHolder {
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
    fun setTypeface(typeface: Typeface, vararg viewIds: Int): CommonRecyclerViewHolder {
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
    fun setProgress(viewId: Int, progress: Int): CommonRecyclerViewHolder {
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
    fun setProgress(viewId: Int, progress: Int, max: Int): CommonRecyclerViewHolder {
        val view = getView<ProgressBar>(viewId)
        view.max = max
        view.progress = progress
        return this
    }

    fun setMax(viewId: Int, max: Int): CommonRecyclerViewHolder {
        val view = getView<ProgressBar>(viewId)
        view.max = max
        return this
    }

    fun setRating(viewId: Int, rating: Float): CommonRecyclerViewHolder {
        val view = getView<RatingBar>(viewId)
        view.rating = rating
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): CommonRecyclerViewHolder {
        val view = getView<RatingBar>(viewId)
        view.max = max
        view.rating = rating
        return this
    }

    fun setTag(viewId: Int, tag: Any): CommonRecyclerViewHolder {
        val view = getView<View>(viewId)
        view.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any): CommonRecyclerViewHolder {
        val view = getView<View>(viewId)
        view.setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): CommonRecyclerViewHolder {
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
    ): CommonRecyclerViewHolder {
        val view = getView<View>(viewId)
        view.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(
        viewId: Int,
        listener: View.OnTouchListener
    ): CommonRecyclerViewHolder {
        val view = getView<View>(viewId)
        view.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(
        viewId: Int,
        listener: View.OnLongClickListener
    ): CommonRecyclerViewHolder {
        val view = getView<View>(viewId)
        view.setOnLongClickListener(listener)
        return this
    }
}