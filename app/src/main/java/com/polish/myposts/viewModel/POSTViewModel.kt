package com.polish.myposts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polish.myposts.data.network.NetworkRepository
import com.polish.myposts.model.POST
import kotlinx.coroutines.*

class POSTViewModel: ViewModel() {

    private val repository = NetworkRepository()
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private val _myListOfPost = MutableLiveData<List<POST>>()
    val myListOfPost:LiveData<List<POST>>
        get() = _myListOfPost


    fun viewMyPostHere(): LiveData<List<POST>> {

        scope.launch {

            _myListOfPost.value = repository.getMyPost()

        }
        return _myListOfPost
    }


    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}