package com.polish.myposts.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.polish.myposts.data.api.PostAPI
import com.polish.myposts.model.POST
import com.polish.myposts.utils.BaseRepository
import com.polish.myposts.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository():BaseRepository() {

    val postApi = PostAPI()

    val TAG = "NETWORK REPOSITORY"



    // Posts
//    suspend fun getMyPost(): List<POST>{
//
//        var result:List<POST> = listOf()
//
//      withContext(Dispatchers.IO){
//
//           result = postApi.viewPosts()
//
//      }
//        Log.d(TAG, "what is inside this:${result}")
//        return result
//
//    }

    suspend fun getMyPost(): Result<List<POST>> {

        return withContext(Dispatchers.IO){

            try {

                Result.Success(postApi.viewPosts())

            } catch (t:Throwable){
                Log.e(TAG, t.message.toString())
                Result.Error(t as Exception)
            }

        }

    }



}