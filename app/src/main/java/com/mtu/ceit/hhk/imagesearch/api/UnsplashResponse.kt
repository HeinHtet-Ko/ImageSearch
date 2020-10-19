package com.mtu.ceit.hhk.imagesearch.api

import com.mtu.ceit.hhk.imagesearch.data.UnsplashPhoto

data class UnsplashResponse(
    val results:List<UnsplashPhoto>
)