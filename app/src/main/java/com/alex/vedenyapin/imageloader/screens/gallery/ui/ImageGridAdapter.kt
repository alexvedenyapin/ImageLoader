package com.alex.vedenyapin.imageloader.screens.gallery.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.alex.vedenyapin.imageloader.R
import com.alex.vedenyapin.imageloader.model.Image
import com.squareup.picasso.Picasso


/**
 * Created by Alex Vedenyapin on 31.08.2018
 */
class ImageGridAdapter: RecyclerView.Adapter<ImageGridAdapter.ViewHolder>() {

    private lateinit var imageList: List<Image>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(imageList[position], position)

    override fun getItemCount(): Int {
        return if (::imageList.isInitialized) imageList.size else 0
    }

    fun updateImageList(imageList: List<Image>) {
        this.imageList = imageList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image, pos: Int) = with(itemView) {
            val imageView = findViewById(R.id.image) as ImageView
            Picasso.get().load(image.thumbnailUrl).into(imageView)
            imageView.setOnClickListener {
                Toast.makeText(context, "Clicked Image # $pos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}