package com.example.appnativa.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.appnativa.EngineBindingsDelegate
import com.example.appnativa.views.utils.DataModel
import com.example.appnativa.views.utils.SettingMethodChannel
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

    //se recupera la pantalla precargada en caché y se le pasa como su proveedor
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

    override fun onDestroy() {
        super.onDestroy()
        SettingMethodChannel.detach()
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