package com.example.appnativa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.appnativa.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        EngineBindings(
            this,
            entrypoint = "MainProductDetail",
            cacheId = "ModuleFlutterProductDetail"
        ).init()

        EngineBindings(
            this,
            entrypoint = "MainMyDiscount",
            cacheId = "ModuleFlutterMyDiscount"
        ).init()

        with(binding) {
            buttonFlutter.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, ModuleFlutterProductDetail::class.java)
                )
            }

            buttonFlutter2.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, ModuleFlutterMyDiscount::class.java)
                )
            }
        }
    }
}