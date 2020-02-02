package com.pgf.mediamonks.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pgf.mediamonks.R
import com.pgf.mediamonks.core.api.model.PhotoItem
import com.pgf.mediamonks.core.exception.Failure
import com.pgf.mediamonks.core.extensions.failure
import com.pgf.mediamonks.core.extensions.observe
import com.pgf.mediamonks.core.extensions.viewModel
import com.pgf.mediamonks.ui.base.BaseFragment
import com.pgf.mediamonks.ui.detail.PhotoDetailFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.info

class PhotoListFragment : BaseFragment(), MainAdapter.ItemClickListener {

    companion object {

        val DEMO_ALBUM_ID = 1

        fun newInstance() = PhotoListFragment()
    }

    override fun layoutId(): Int = R.layout.fragment_main

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(layoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel = viewModel(viewModelFactory) {
            observe(photos, ::onPhotosRetrieved)
            failure(failure, ::onFailure)
        }

        rvPhotos.layoutManager = LinearLayoutManager(context)
        mainAdapter = MainAdapter(emptyList(), this)
        rvPhotos.adapter = mainAdapter
        rvPhotos.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        showProgress()
        mainViewModel.getPhotos(DEMO_ALBUM_ID)
    }

    private fun onPhotosRetrieved(photos: List<PhotoItem>?) {

        hideProgress()

        photos?.let { mainAdapter.updateData(photos) }
    }

    private fun onFailure(failure: Failure?) {
        hideProgress()
        Toast.makeText(context, "No Data found", Toast.LENGTH_LONG).show()
        info("onFailure: $failure")
    }

    override fun onItemClick(item: PhotoItem) {

        val fragmentTransaction = fragmentManager?.beginTransaction()
        if (fragmentTransaction != null) {

            fragmentTransaction.replace(
                R.id.container,
                PhotoDetailFragment.newInstance(item)
            )
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}
