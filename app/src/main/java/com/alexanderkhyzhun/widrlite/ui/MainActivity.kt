package com.alexanderkhyzhun.widrlite.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexanderkhyzhun.widrlite.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    companion object {
        private const val OFFSCREEN_PAGE_LIMIT = 5
        const val TAG = "MainActivity"
        const val colorTransparent = "#FFFFFFFF"
        fun getIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }
}
