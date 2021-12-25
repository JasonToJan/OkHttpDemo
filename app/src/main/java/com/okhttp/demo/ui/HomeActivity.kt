package com.okhttp.demo.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.okhttp.demo.R

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun jumpToNet(view: View) {
        Intent(this, NetActivity::class.java).run {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }

    fun jumpToRxJava(view: View) {
        Intent(this, RxJavaActivity::class.java).run {
            startActivity(this)
        }
    }

    fun jumpToGlide(view: View) {
        Intent(this, GlideActivity::class.java).run {
            startActivity(this)
        }
    }
}