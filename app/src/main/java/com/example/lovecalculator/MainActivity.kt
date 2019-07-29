package com.example.lovecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.lovecalculator.api.LoveRequester
import com.example.lovecalculator.data.LoveCalculationResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val loveRequester = LoveRequester()

    private val callback = object : Callback<LoveCalculationResult> {
        override fun onFailure(call: Call<LoveCalculationResult>, t: Throwable) {
            Toast.makeText(this@MainActivity, "Network request failed", Toast.LENGTH_LONG).show()
        }

        override fun onResponse(call: Call<LoveCalculationResult>, response: Response<LoveCalculationResult>) {
            response?.isSuccessful.let {
                tv_percentage.text = response.body()?.percentage
                tv_result.text = response.body()?.result
                tv_result.visibility = View.VISIBLE
                tv_percentage.visibility = View.VISIBLE

                response.body()?.percentage?.let {
                    Thread(Runnable {
                        var i = 0
                        while (i < response.body()?.percentage!!.toInt()) {
                            pb_score.progress = i
                            i += 1

                            try {
                                Thread.sleep(100)
                            } catch (e: InterruptedException) {
                                e.printStackTrace()
                            }


                        }
                    }).start()
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hitme.setOnClickListener {
            if (et_fname.text.isEmpty() || et_sname.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Insert proper names", Toast.LENGTH_LONG).show()
                pb_score.progress = 0
            } else {
                loveRequester.getPercentage(callback, et_fname.text.toString(), et_sname.text.toString())
            }
        }
    }
}

class callbackResult {
    companion object {
        var response: Int = 0
    }
}
