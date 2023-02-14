package com.example.appnativa.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.appnativa.EngineBindings
import com.example.appnativa.R
import com.example.appnativa.databinding.ActivityMainBinding
import com.example.appnativa.views.fragments.HomeNativeFragment
import com.example.appnativa.views.fragments.ModuleFlutterFragment
import com.example.appnativa.views.utils.DataModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import io.flutter.embedding.android.FlutterFragment
import kotlin.time.Duration.Companion.days


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var homeFragment: HomeNativeFragment = HomeNativeFragment()
    private var flutterFragment: ModuleFlutterFragment = ModuleFlutterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        EngineBindings(
            this,
            entrypoint = "flutterEntryPoint",
            cacheId = "flutter"
        ).init()

        loadFragment(homeFragment)

        with(binding) {
            navView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> {
                        loadFragment(homeFragment)
                        return@setOnItemSelectedListener true
                    }
                    R.id.navigation_flutter -> {
                        loadFragment(flutterFragment)
                        return@setOnItemSelectedListener true
                    }
                }
                return@setOnItemSelectedListener false
            }
        }
    }

    fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentTag = "my_fragment_tag"
        transaction.replace(R.id.fragmentContainer, fragment, fragmentTag);
        transaction.addToBackStack(fragmentTag)
        transaction.commit()
    }
}