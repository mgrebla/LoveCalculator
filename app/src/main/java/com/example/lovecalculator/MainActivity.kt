package com.example.lovecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hitme.setOnClickListener {
            if (et_fname.text.isEmpty() || et_sname.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Insert proper names", Toast.LENGTH_LONG).show()
            } else {
                loveRequester.getPercentage(callback, et_fname.text.toString(), et_sname.text.toString())
            }
        }
    }
}
