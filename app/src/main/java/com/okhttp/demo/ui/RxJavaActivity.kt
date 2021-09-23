package com.okhttp.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.okhttp.demo.R
import com.okhttp.demo.utils.RxJavaUtils
import io.reactivex.Observable

class RxJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)

        testRxJava()
    }

    private fun testRxJava() {
        RxJavaUtils.makeMultiObservable()
    }
}