package com.wlx.xmood.ui.mood.edit

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import com.wlx.xmood.R

class CategoryItemAdapter(
    val context : Context,
    val list : ArrayList<CategoryItem>
    ) : BaseAdapter() {

    inner class ViewHolder () {
        lateinit var category: TextView
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View ?= null
        var viewHolder: ViewHolder ?= null

        if(convertView == null){
            viewHolder = ViewHolder()
            view = LayoutInflater.from(context).inflate(R.layout.category_grid_item, null)
            viewHolder.category = view.findViewById(R.id.ctg_grid_text_item)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.category.text = list[position].category
        if(list[position].selected == 1){
            viewHolder.category.setBackgroundResource(R.drawable.category_item_selected)
            viewHolder.category.setTypeface(Typeface.DEFAULT_BOLD)
        }
        else{
            viewHolder.category.setBackgroundResource(R.drawable.category_item_unselected)
            viewHolder.category.setTypeface(Typeface.DEFAULT)
        }
        viewHolder.category.setOnClickListener(
            View.OnClickListener { view ->
                run {
                    if(list[position].selected == 0){
                        list[position].selected = 1
                        viewHolder.category.setBackgroundResource(R.drawable.category_item_selected)
                        viewHolder.category.setTypeface(Typeface.DEFAULT_BOLD)
                    }
                    else{
                        list[position].selected = 0
                        viewHolder.category.setBackgroundResource(R.drawable.category_item_unselected)
                        viewHolder.category.setTypeface(Typeface.DEFAULT)
                    }
                }
        })

        return view!!
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }
}