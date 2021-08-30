package com.okhttp.demo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.okhttp.demo.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun jumpToNet(view: View) {
        Intent(this, NetActivity::class.java).run {
            startActivity(this)
        }
    }

    fun jumpToRxJava(view: View) {
        Intent(this, RxJavaActivity::class.java).run {
            startActivity(this)
        }
    }
}