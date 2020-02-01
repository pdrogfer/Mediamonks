package com.pgf.mediamonks.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pgf.mediamonks.R
import com.pgf.mediamonks.core.api.model.PhotoItem
import com.squareup.picasso.Picasso

class MainAdapter(private var photos: List<PhotoItem>, private var clickListener: ItemClickListener) :
    RecyclerView.Adapter<MainAdapter.PhotoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        val viewInflated = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_photo, parent, false)

        return PhotoViewHolder(viewInflated)
    }

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]

        Picasso.get()
            .load(photo.thumbnailUrl)
            .centerCrop()
            .into(holder.thumbnail)

        holder.title.text = photo.title
        holder.id.text = photo.id.toString()

        holder.rootView.setOnClickListener {
            clickListener.onItemClick(photo)
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var rootView: View = itemView
        var title: TextView = rootView.findViewById(R.id.item_photo_title)
        var thumbnail: ImageView = rootView.findViewById(R.id.item_photo_thumb)
        var id: TextView = rootView.findViewById(R.id.item_photo_id)
    }

    interface ItemClickListener {
        fun onItemClick(item: PhotoItem)
    }
}
