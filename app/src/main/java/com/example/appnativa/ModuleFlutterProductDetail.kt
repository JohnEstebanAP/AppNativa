package com.example.appnativa

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech.Engine
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

class ModuleFlutterProductDetail : FlutterActivity(), EngineBindingsDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //se recupera la pantalla precargada en caché y se lo pasa como su proveedor
    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance().get("ModuleFlutterProductDetail")
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
        startActivity(Intent(this, MyDiscountActivity::class.java))
    }

    override fun onBack() {
        onBackPressed()
    }
}