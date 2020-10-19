package com.mtu.ceit.hhk.imagesearch.ui.gallery

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mtu.ceit.hhk.imagesearch.R
import com.mtu.ceit.hhk.imagesearch.databinding.FragmentGalleryBinding
import com.mtu.ceit.hhk.imagesearch.ui.adapter.UnsplashPhotoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gallery.*

@AndroidEntryPoint
class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    private val galleryViewModel by viewModels<GalleryViewModel>()
    private var _binding:FragmentGalleryBinding ? = null
    private val binding:FragmentGalleryBinding get() = _binding!!
    private val _adapter = UnsplashPhotoAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGalleryBinding.bind(view)

        recyclerSetup()



    }

    fun recyclerSetup()
    {
       binding.galleryRecycler.apply {
           setHasFixedSize(true)
           adapter = _adapter
       }

        observePhotos()
    }

    fun observePhotos(){
        galleryViewModel.photos.observe(viewLifecycleOwner)
        {
            _adapter.submitData(viewLifecycleOwner.lifecycle,it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }




}