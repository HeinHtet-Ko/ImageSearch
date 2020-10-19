package com.mtu.ceit.hhk.imagesearch.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mtu.ceit.hhk.imagesearch.data.UnsplashRepository

class GalleryViewModel @ViewModelInject
constructor(private val repos:UnsplashRepository)
    :ViewModel() {



    val currentQuery = MutableLiveData(STARTER)

    val photos = currentQuery.switchMap { it ->
        repos.getSearchResults(it).cachedIn(viewModelScope)
    }

    fun searchPhotos(query:String)
    {
        currentQuery.value = query
    }


    companion object {
        private const val STARTER = ""
    }
}