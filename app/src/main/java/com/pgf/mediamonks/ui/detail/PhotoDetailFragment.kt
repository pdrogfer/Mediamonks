package com.pgf.mediamonks.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pgf.mediamonks.R
import com.pgf.mediamonks.core.api.model.PhotoItem
import com.pgf.mediamonks.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_photo_detail.*

class PhotoDetailFragment : BaseFragment() {

    companion object {

        val KEY_PHOTO = "KEY_PHOTO"

        fun newInstance(photoItem: PhotoItem): PhotoDetailFragment {

            val fragment = PhotoDetailFragment()
            val args = Bundle()
            args.putParcelable(KEY_PHOTO, photoItem)
            fragment.arguments = args

            return fragment
        }
    }

    private var photo: PhotoItem? = null

    override fun layoutId(): Int = R.layout.fragment_photo_detail

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPhotoFromExtras()
        initViews()
    }

    private fun getPhotoFromExtras() {

        arguments?.let {
            photo = it.getParcelable(KEY_PHOTO)
        }
    }

    private fun initViews() {

        photo?.let {

            Picasso.get()
                .load(it.url)
                // .resize(1024, 1024)
                .fit()
                .centerCrop()
                .error(R.drawable.image_placeholder_error)
                .into(detail_image)

            detail_title.text = it.title
        }
    }
}