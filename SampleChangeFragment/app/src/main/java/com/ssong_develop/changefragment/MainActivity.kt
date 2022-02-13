package com.ssong_develop.changefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    private var number = 0
    private val listener = FragmentManager.OnBackStackChangedListener {
        // onBackStackChanged
        // it represent SAM interface
        val fragmentManager = supportFragmentManager
        var count = 0
        fragmentManager.fragments.filterNotNull().forEach { _ ->
            count++
        }
        number = count
        Log.d("MainActivity","onBackStackChanged number = ${number}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.add_btn).setOnClickListener {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container,SampleFragment.newInstance(number))
                .addToBackStack(null)
                .commit()
        }

        findViewById<Button>(R.id.remove_btn).setOnClickListener {
            if(number == 0) return@setOnClickListener
            val fragmentManager = supportFragmentManager
            fragmentManager.popBackStack()
        }

        val fragmentManager = supportFragmentManager
        fragmentManager.addOnBackStackChangedListener(listener)
        val fragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
        if(savedInstanceState == null){
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container,SampleFragment.newInstance(number), FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroy() {
        val fragmentManager = supportFragmentManager
        fragmentManager.removeOnBackStackChangedListener(listener)
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_NUMBER,number)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        number = savedInstanceState.getInt(KEY_NUMBER)
    }

    companion object {
        private const val FRAGMENT_TAG = "FRAGMENT_TAG"
        private const val KEY_NUMBER = "KEY_NUMBER"

    }
}