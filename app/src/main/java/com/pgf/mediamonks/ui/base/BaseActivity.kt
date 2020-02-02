package com.pgf.mediamonks.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pgf.mediamonks.R
import com.pgf.mediamonks.R.id
import com.pgf.mediamonks.core.extensions.inTransaction
import org.jetbrains.anko.AnkoLogger

abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity_layout)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(
            id.container) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction { add(
            id.container, fragment()) }

    abstract fun fragment(): BaseFragment
}
