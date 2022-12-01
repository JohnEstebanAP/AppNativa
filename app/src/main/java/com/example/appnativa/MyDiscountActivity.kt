package com.example.appnativa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.appnativa.databinding.ActivityMydiscountBinding

class MyDiscountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMydiscountBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mydiscount)

        with(binding) {

            buttonMyDiscount.setOnClickListener {
                startActivity(
                    Intent(this@MyDiscountActivity, ModuleFlutterMyDiscount::class.java)
                )
            }

            buttonBackFlutter.setOnClickListener {
                onBackPressed()
            }
        }
    }
}