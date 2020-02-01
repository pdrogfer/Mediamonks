package com.pgf.mediamonks.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pgf.mediamonks.R
import com.pgf.mediamonks.ui.base.BaseActivity
import com.pgf.mediamonks.ui.base.BaseFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MainFragment.newInstance())
                .commitNow()
    }

    override fun fragment(): BaseFragment = MainFragment()
}

