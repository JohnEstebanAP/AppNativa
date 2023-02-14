package com.example.appnativa.views.utils

import android.util.Log
import com.example.appnativa.EngineBindingsDelegate
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
            Companion.delegate = delegate
            attach()
        }

        @JvmStatic
        fun getDelegate(): String {
            return Companion.delegate.toString()
        }

        private fun attach() {
            channel.setMethodCallHandler { call, result ->
                when (call.method) {
                    "sendFlutterMessage" -> {
                        delegate.reseiveMesaggeOfFlutter(call.arguments.toString())
                        result.success(null)
                    }
                    "backToHome" -> {
                        delegate.onBack()
                        result.success(null)
                    }
                    "next" -> {
                        delegate.onNext()
                        result.success(null)
                    }
                    else -> {
                        result.notImplemented()
                    }
                }
            }
        }

        fun sendFlutterMessage(message: String) {
            try {
                channel.invokeMethod("getIOSNativeMessage", message)
            } catch (exception: Exception) {
                Log.w("PlatformException", "$exception")
            }
        }
    }


    init {
        channel = MethodChannel(
            engine.dartExecutor.binaryMessenger,
            "flutter"
        )
        attach()
        isStart = true
    }

}