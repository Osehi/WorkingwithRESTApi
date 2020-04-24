package com.polish.myposts.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.polish.myposts.data.api.PostAPI
import com.polish.myposts.model.POST
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository {

    val postApi = PostAPI()

    val TAG = "NETWORK REPOSITORY"



    // Posts
    suspend fun getMyPost(): List<POST>{

        var result:List<POST> = listOf()

      withContext(Dispatchers.IO){

           result = postApi.viewPosts()
//          Log.d(TAG, "here is my output:$result")
      }
        Log.d(TAG, "what is inside this:${result}")
        return result

    }

}