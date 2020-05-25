package com.polish.myposts.bindingUtils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.polish.myposts.model.POST

@BindingAdapter("titlePOST")
fun TextView.setTitlePost(post:POST){
    text = post.title
}

@BindingAdapter("contentPOST")
fun TextView.setContentPost(post:POST){
    text = post.body
}