package com.example.appnativa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.appnativa.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        EngineBindings(
            this,
            entrypoint = "flutterEntryPoint",
            cacheId = "flutter"
        ).init()

        with(binding) {

            DataModel.instance._message.observe(this@MainActivity, Observer {
                messageGet.text = it
            })

            buttonFlutter.setOnClickListener {
                val intent: Intent = Intent(this@MainActivity, ModuleFlutter::class.java)
                intent.putExtra("MESSAGE", messageFromNative.text.toString())
                startActivity(intent)
            }
        }
    }
}