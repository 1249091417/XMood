package com.wlx.xmood.ui.mood.edit

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.*
import com.wlx.xmood.BaseActivity
import com.wlx.xmood.R
import com.wlx.xmood.ui.daily.edit.TimePickerFragment
import com.wlx.xmood.utils.Utils


class MoodEditActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    val list: ArrayList<CategoryItem> = ArrayList()
    val texts = arrayOf<String>("Happy", "Sad", "Angry", "Lonely")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mood_edit)
        val moodId = intent.getStringExtra("moodId")
        if (moodId == null) {

        }

        //时间选择器触发事件
        val timeText : TextView = findViewById(R.id.mood_edit_text_time)
        timeText.setOnClickListener {
            TimePickerFragment().show(supportFragmentManager,"timepicker")
        }

        //下拉栏
        val spinner: Spinner = findViewById(R.id.rating_spinner)

        ArrayAdapter.createFromResource(
            this,R.array.rating_spinner_items,
            android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                // Apply the adapter to the spinner
                spinner.adapter = adapter
        }

        //下拉栏监听事件
        spinner.onItemSelectedListener = this

        //分类标签
        initCategoryItem()
        val gridView: GridView = findViewById(R.id.grid_category)
        gridView.setSelector(R.drawable.category_item)
        gridView.adapter = CategoryItemAdapter(this, list)

        //返回按钮
        val backBtn : ImageView = findViewById(R.id.mood_edit_back)
        backBtn.setOnClickListener {
            Utils.makeToast(this,"已取消")
            finish()
        }

        //保存按钮
        val saveBtn : Button = findViewById(R.id.mood_edit_save_btn)
        saveBtn.setOnClickListener {
            save()
            Utils.makeToast(this,"已保存")
            finish()
        }

    }

    private fun initCategoryItem(){
        for(i in 1..10){
            val randomNum = (0..3).random()
            list.add(CategoryItem(texts[randomNum],0))
        }
    }

    private fun save(){

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val rating = parent?.getItemAtPosition(position).toString()
        Utils.makeToast(this, "选择Rating: $rating")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Utils.makeToast(this, "nothing select!")
    }


}