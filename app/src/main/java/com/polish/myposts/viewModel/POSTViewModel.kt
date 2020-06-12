package com.polish.myposts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polish.myposts.data.network.NetworkRepository
import com.polish.myposts.model.POST
import com.polish.myposts.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class POSTApiStatus { LOADING, ERROR, DONE }

class POSTViewModel: ViewModel() {

    private val repository = NetworkRepository()
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

//    private val _myListOfPost = MutableLiveData<List<POST>>()
//        val myListOfPost:LiveData<List<POST>>
//        get() = _myListOfPost

    private val _myListOfPost = MutableLiveData<Result<List<POST>>>()
    val myListOfPost:LiveData<Result<List<POST>>>
    get() = _myListOfPost

    private val _navigateToPostDetail = MutableLiveData<POST>()
    val navigateToPostDetail
    get() = _navigateToPostDetail

    private val _status = MutableLiveData<POSTApiStatus>()
    val status:LiveData<POSTApiStatus>
    get() = _status


//    fun viewMyPostHere(): LiveData<List<POST>> {
//
//        scope.launch {
//
//            _myListOfPost.value = repository.getMyPost()
//
//        }
//        return _myListOfPost
//    }


    // click handler
    // this will trigger the navigation
    fun onPostClicked(post: POST){
        _navigateToPostDetail.value = post
    }

    // define the method after the app has finished navigating
    fun onPostDetailNavigated(){
        _navigateToPostDetail.value = null
    }


    fun viewMyPostHere(): LiveData<Result<List<POST>>>{

        scope.launch {

            try {
                _status.value = POSTApiStatus.LOADING
                _myListOfPost.value = repository.getMyPost()

                _status.value = POSTApiStatus.DONE

            } catch (e:Exception){
                _status.value = POSTApiStatus.ERROR
            }



        }

        return _myListOfPost

    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }


}