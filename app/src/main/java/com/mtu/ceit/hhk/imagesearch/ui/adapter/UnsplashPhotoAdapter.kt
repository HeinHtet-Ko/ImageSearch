package com.mtu.ceit.hhk.imagesearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mtu.ceit.hhk.imagesearch.R
import com.mtu.ceit.hhk.imagesearch.data.UnsplashPhoto
import com.mtu.ceit.hhk.imagesearch.databinding.UnsplashPhotoItemBinding
import kotlinx.android.synthetic.main.fragment_gallery.*

class UnsplashPhotoAdapter:PagingDataAdapter<UnsplashPhoto,UnsplashPhotoAdapter.UnsplashViewHolder>(
    DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnsplashViewHolder {

        val binding = UnsplashPhotoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)


        return UnsplashViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {

        val currentPhoto = getItem(position)

        if(currentPhoto != null){
            holder.bindViews(currentPhoto)
        }

    }


    inner class UnsplashViewHolder(val binding:UnsplashPhotoItemBinding):RecyclerView.ViewHolder(binding.root)
    {

            fun bindViews(photo:UnsplashPhoto)
            {

                binding.apply {
                    Glide
                        .with(itemView)
                        .load(photo.urls.regular)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.error)
                        .centerCrop()
                        .into(unsplashItemImageview)

                    unsplashItemUsername.text = photo.user.username
                }
            }
    }

    companion object
    {
        private val DIFF_CALLBACK = object :DiffUtil.ItemCallback<UnsplashPhoto>(){
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ) =
                oldItem == newItem
        }
    }
}