package com.pgf.mediamonks.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pgf.mediamonks.core.dependency_injection.AppApplication
import com.pgf.mediamonks.core.dependency_injection.ApplicationComponent
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import javax.inject.Inject

abstract class BaseFragment : Fragment(), AnkoLogger {

    abstract fun layoutId(): Int

    val appComponent: ApplicationComponent by lazy(LazyThreadSafetyMode.NONE) {
        (activity?.application as AppApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId(), container, false)

    internal fun showProgress() = progressStatus(View.VISIBLE)
    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) {
        with(activity) {
            if (this is BaseActivity) this.progress.visibility = viewStatus
        }
    }

    open fun onBackPressed() {}
}