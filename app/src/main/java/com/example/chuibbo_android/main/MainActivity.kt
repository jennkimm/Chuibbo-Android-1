package com.example.chuibbo_android.main

import GuidelineFragment
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.analyzer.Dependency
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.chuibbo_android.R
import com.example.chuibbo_android.home.HomeFragment
import com.example.chuibbo_android.mypage.MypageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  { // PreferenceFragmentCompat.OnPreferenceStartFragmentCallback

//     var customFragmentFactory = CustomFragmentFactory("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        bottomNavigationView.background = null
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        bottomNavigationView.selectedItemId = R.id.home_item

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_action_previous)

        // TODO: 카메라 탭 클릭 시, 카메라 탭 활성화 및 다른 탭 비활성화
        camera_fab.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, GuidelineFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        onBackPressed()
        return true
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.home_item -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, HomeFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
            R.id.mypage_item -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, MypageFragment())
                transaction.addToBackStack(null)
                transaction.commit()
                return true
            }
        }
        return false
    }
}
