package com.wlx.xmood.settings.theme

import android.os.Bundle
import android.widget.ImageView
import com.wlx.xmood.BaseActivity
import com.wlx.xmood.R

class ThemeSelectActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_select)
        val back: ImageView = findViewById(R.id.back)
        back.setOnClickListener {
            finish()
        }
    }
}