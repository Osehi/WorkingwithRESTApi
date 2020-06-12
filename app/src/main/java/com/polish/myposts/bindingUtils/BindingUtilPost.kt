package com.polish.myposts.bindingUtils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.polish.myposts.R
import com.polish.myposts.model.POST
import com.polish.myposts.viewModel.POSTApiStatus

@BindingAdapter("titlePOST")
fun TextView.setTitlePost(post:POST){
    text = post.title
}

@BindingAdapter("contentPOST")
fun TextView.setContentPost(post:POST){
    text = post.body
}

@BindingAdapter("postApiStatus")
fun bindStatus(statusImageView: ImageView, status: POSTApiStatus?){
    when(status){
        POSTApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        POSTApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_error)
        }

        POSTApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

    }

}