package com.example.appnativa.views.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.appnativa.databinding.FragmentHomeNativeBinding
import com.example.appnativa.views.ModuleFlutter
import com.example.appnativa.views.utils.DataModel
import com.example.appnativa.views.utils.SettingMethodChannel

class HomeNativeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeNativeFragment()
    }

    private lateinit var binding: FragmentHomeNativeBinding
    private lateinit var viewModel: HomeNativeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeNativeBinding.inflate(inflater, container, false)

        with(binding) {

            DataModel.instance.message.observe(viewLifecycleOwner, Observer {
                messageGet.text = it
            })

            messageFromNative.addTextChangedListener(afterTextChanged = { listener ->
                DataModel.instance.editTextMessageNative = listener.toString()
            })

            buttonFlutter.setOnClickListener {
                val intent: Intent = Intent(context, ModuleFlutter::class.java)
                intent.putExtra("MESSAGE", messageFromNative.text.toString())
                startActivity(intent)
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeNativeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        SettingMethodChannel.detach()
        DataModel.instance.removeObserver()
    }
}