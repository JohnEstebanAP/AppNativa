package com.example.appnativa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

class ModuleFlutter : FlutterActivity(), EngineBindingsDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onFlutterUiDisplayed() {
        super.onFlutterUiDisplayed()

        val extras: Bundle? = intent.extras
        if (extras != null) {
            val message: String = extras.getString("MESSAGE").toString()
            SettingMethodChannel.sendFlutterMessage(message)
        }
    }

    //se recupera la pantalla precargada en caché y se lo pasa como su proveedor
    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance().get("flutter")
    }

    //se le pasa el canal de comunicación previamente creado,
    // si este no existe se crea el canal de comunicación.
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        if (!SettingMethodChannel.isStart) {
            SettingMethodChannel(flutterEngine)
        }
        SettingMethodChannel.setDelegate(this)
    }


    override fun onNext() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBack() {
        onBackPressed()
    }

    override fun reseiveMesaggeOfFlutter(message: String) {
        DataModel.instance.updateMessage(message)
    }
}