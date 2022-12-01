package com.example.appnativa

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class SettingMethodChannel(engine: FlutterEngine) {

    companion object {
        lateinit var channel: MethodChannel
        private lateinit var delegate: EngineBindingsDelegate;
        var isStart: Boolean = false

        @JvmStatic
        fun detach() {
            channel.setMethodCallHandler(null)
        }

        @JvmStatic
        fun setDelegate(delegate: EngineBindingsDelegate) {
            this.delegate = delegate
            attach()
        }

        private fun attach() {
            channel.setMethodCallHandler { call, result ->
                when (call.method) {
                    "next" -> {
                        Log.d("NextPage", "Startpage")
                        this.delegate.onNext()
                        result.success(null)
                    }
                    "back" -> {
                        this.delegate.onBack()
                        result.success(null)
                    }
                    "getProductPageInfo" -> {
                        result.success("data")
                    }
                    else -> {
                        result.notImplemented()
                    }
                }
            }
        }
    }

    init {
        channel = MethodChannel(
            engine.dartExecutor.binaryMessenger,
            "com.example.flutter_module"
        )
        attach()
        isStart = true
    }

}