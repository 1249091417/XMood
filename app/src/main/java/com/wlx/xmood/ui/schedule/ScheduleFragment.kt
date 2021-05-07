package com.wlx.xmood.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wlx.xmood.R
import com.wlx.xmood.utils.TimeUtil

class ScheduleFragment : Fragment() {

    private lateinit var scheduleViewModel: ScheduleViewModel

    private val lessonList = ArrayList<LessonItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)
//        val textView: TextView = root.findViewById(R.id.text_schedule)
//        scheduleViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        init()
        val toolbar: Toolbar = root.findViewById(R.id.toolbar_schedule)
        toolbar.inflateMenu(R.menu.schedule_tool_bar)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add_schedule ->
                    Toast.makeText(context, "add_schedule", Toast.LENGTH_SHORT).show()
            }
            true
        }
        val recyclerView: RecyclerView = root.findViewById(R.id.schedule_today_list)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = LessonAdapter(this, lessonList)
        return root
    }

    private fun init() {
        lessonList.add(
            LessonItem(
                "计算理论基础啦啦啦啦啦啦", "田家炳教育书院236",1, 1,
                TimeUtil.Str2Long("08:00", "HH:mm"),
                TimeUtil.Str2Long("09:40", "HH:mm")
            )
        )
        lessonList.add(
            LessonItem(
                "计算理论基础", "田家炳教育书院236", 1, 2,
                TimeUtil.Str2Long("08:00", "HH:mm"),
                TimeUtil.Str2Long("09:40", "HH:mm")
            )
        )
        lessonList.add(
            LessonItem(
                "计算理论基础", "田家炳教育书院236", 2, 3,
                TimeUtil.Str2Long("08:00", "HH:mm"),
                TimeUtil.Str2Long("09:40", "HH:mm")
            )
        )
    }
}