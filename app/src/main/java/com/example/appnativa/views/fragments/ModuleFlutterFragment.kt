package com.example.appnativa.views.fragments

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appnativa.EngineBindingsDelegate
import com.example.appnativa.R
import com.example.appnativa.views.MainActivity
import com.example.appnativa.views.utils.DataModel
import com.example.appnativa.views.utils.SettingMethodChannel
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache

class ModuleFlutterFragment : FlutterFragment(), EngineBindingsDelegate {

    companion object {
        fun newInstance() = ModuleFlutterFragment()
    }

    private lateinit var viewModel: ModuleFlutterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = super.onCreateView(inflater, container, savedInstanceState)

        SettingMethodChannel.setDelegate(this)
        return view
    }

    override fun onFlutterUiDisplayed() {
        super.onFlutterUiDisplayed()

        SettingMethodChannel.setDelegate(this)
        SettingMethodChannel.sendFlutterMessage(DataModel.instance.editTextMessageNative)

    }

    //se recupera la pantalla precargada en caché y se le pasa como su proveedor
    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance().get("flutter")
    }

    //se le pasa el canal de comunicación previamente creado,
    // si este no existe se crea el canal de comunicación.
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        if (!SettingMethodChannel.isStart) {
            SettingMethodChannel(flutterEngine)
        }
    }

    override fun getRenderMode(): RenderMode {
        val renderModeName =
            requireArguments().getString(ARG_FLUTTERVIEW_RENDER_MODE, RenderMode.texture.name)
        return RenderMode.valueOf(renderModeName)
    }

    override fun onNext() {
        startActivity(Intent(context, MainActivity::class.java))
    }

    override fun onBack() {
        onBackPressed()
    }

    override fun reseiveMesaggeOfFlutter(message: String) {
        DataModel.instance.updateMessage(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        SettingMethodChannel.detach()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ModuleFlutterViewModel::class.java)
        // TODO: Use the ViewModel
    }
}