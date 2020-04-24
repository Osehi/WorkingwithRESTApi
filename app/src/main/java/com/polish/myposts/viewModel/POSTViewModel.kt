package com.polish.myposts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polish.myposts.data.network.NetworkRepository
import com.polish.myposts.model.POST
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class POSTViewModel: ViewModel() {

    private val repository = NetworkRepository()
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private val _myListOfPost = MutableLiveData<List<POST>>()

    fun viewMyPostHere(): LiveData<List<POST>> {

        scope.launch {

            _myListOfPost.value = repository.getMyPost()

        }
        return _myListOfPost
    }

}