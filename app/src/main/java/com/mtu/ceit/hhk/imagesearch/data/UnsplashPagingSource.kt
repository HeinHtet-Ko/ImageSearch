package com.mtu.ceit.hhk.imagesearch.data

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.paging.PagingSource
import com.mtu.ceit.hhk.imagesearch.api.UnsplashApi
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_START_PAGE_INDEX = 1;


class UnsplashPagingSource(private val api:UnsplashApi,
private val query:String ) :PagingSource<Int,UnsplashPhoto>(){


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_START_PAGE_INDEX



        return try {
            val response = api.searchPhoto(query,position,params.loadSize)

            val photos = response.results

            Log.d("TAG", "load: ${photos.size}")

            LoadResult.Page(data = photos,
            prevKey = if(position == UNSPLASH_START_PAGE_INDEX) null else position-1,
            nextKey = if(photos.isEmpty()) null else position+1
                )

        }catch (e:IOException)
        {
            LoadResult.Error(e)
        }catch (e:HttpException)
        {
            LoadResult.Error(e)
        }
    }
}