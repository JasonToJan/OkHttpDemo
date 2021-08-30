package com.okhttp.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.okhttp.demo.R
import com.okhttp.demo.models.BaseData
import com.okhttp.demo.models.HomeTabBean
import com.okhttp.demo.net.okhttp.INet
import com.okhttp.demo.net.okhttp.RequestManager
import com.okhttp.demo.net.retrofit.RetrofitManager
import kotlinx.android.synthetic.main.activity_net.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)
    }

    fun executeOkhttp(view: View) {
        RequestManager.getInstance().requestHomeData("yPViQAfC", true, object : INet<HomeTabBean> {
            override fun onPrepare() {
                am_request_status_tv.text = "正在请求网络..."
            }

            override fun onSuccess(result: HomeTabBean?, msg: String?) {
                am_request_status_tv.text = "请求成功！"

                am_request_result_tv.text = msg
            }

            override fun onFailure(msg: String?) {
                am_request_status_tv.text = "请求失败！！原因：$msg"
            }

        })
    }

    fun executeRetrofit(view: View) {
        RetrofitManager.getInstance().api.getHomeData(RetrofitManager.getInstance().params)
            .enqueue(object : Callback<BaseData<HomeTabBean>> {

                override fun onResponse(call: Call<BaseData<HomeTabBean>>, response: Response<BaseData<HomeTabBean>>) {
                    am_request_status_tv.text = "请求成功！"

                    val body: BaseData<HomeTabBean>? = response.body()
                    val tab = body!!.value

                    am_request_result_tv.text = tab.toString()
                }

                override fun onFailure(call: Call<BaseData<HomeTabBean>>, t: Throwable) {
                    am_request_status_tv.text = "请求失败！！原因：${t.message}"
                }

            })
    }

}