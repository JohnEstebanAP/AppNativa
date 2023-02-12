package com.example.appnativa

import android.app.Activity
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor


interface EngineBindingsDelegate {
    fun onNext()
    fun onBack()
    fun reseiveMesaggeOfFlutter(message: String)
}

class EngineBindings(activity: Activity, entrypoint: String, cacheId: String) {
    val engine: FlutterEngine

    init {
        val app = activity.applicationContext as App
        val dartEntrypoint = DartExecutor.DartEntrypoint(
            FlutterInjector.instance().flutterLoader().findAppBundlePath(), entrypoint
        )
        engine = app.engines.createAndRunEngine(activity, dartEntrypoint)
        //se activa el canal de comunicación para cada página
        SettingMethodChannel(engine)
        FlutterEngineCache
            .getInstance()
            .put(cacheId, engine)
    }

    fun init() {}
}