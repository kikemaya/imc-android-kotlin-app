package com.kikemaya.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kikemaya.imc.MainActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvDescription: TextView

    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)

        val result: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvImc.text = result.toString()

        when (result) {
            // LOW WEIGHT
            in 0.00..18.50 -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.low_weight))
                tvResult.text = getString(R.string.low_weight_title)
                tvDescription.text = getString(R.string.low_weight_description)
            }
            // EXPECTED WEIGHT
            in 18.51..24.99 -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                tvResult.text = getString(R.string.title_normal)
                tvDescription.text = getString(R.string.normal_weight_description)
            }
            //OVERWEIGHT
            in 25.00..29.99 -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.over_weight))
                tvResult.text = getString(R.string.overweight_title)
                tvDescription.text = getString(R.string.overweight_description)
            }
            //OBESITY
            in 30.00..99.99 -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity_weight))
                tvResult.text = getString(R.string.obesity_title)
                tvDescription.text = getString(R.string.obesity_description)
            }
            //ERROR
            else -> {
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity_weight))
                tvImc.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvImc = findViewById(R.id.tvImc)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)

        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}