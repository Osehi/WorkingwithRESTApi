package com.polish.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.polish.myposts.adapter.POSTAdapter
import com.polish.myposts.databinding.ActivityMainBinding
import com.polish.myposts.model.POST
import com.polish.myposts.utils.Result
import com.polish.myposts.viewModel.POSTViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var button:Button
    lateinit var viewModel: POSTViewModel
    lateinit var adapter:POSTAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(POSTViewModel::class.java)

        val binding = ActivityMainBinding.inflate(layoutInflater)

//        binding.viewButtonId.setOnClickListener {
//
//            viewModel.viewMyPostHere()
//
//        }
        button = findViewById(R.id.view_buttonId)
        button.setOnClickListener {
            viewModel.viewMyPostHere()
        }


//        val recyclerView = binding.myRecyclerViewId
        val recyclerView:RecyclerView = findViewById(R.id.myRecyclerViewId)
        recyclerView.layoutManager = LinearLayoutManager(this)

         adapter = POSTAdapter()
        recyclerView.adapter = adapter

//        viewModel.myListOfPost.observe(this, Observer {
//            it?.let {
//                Log.d(TAG, "show me inside:${it}")
//                adapter.submitList(it)
//            }
//        })

        viewModel.myListOfPost.observe(this, Observer {

            when(it){

                is Result.Success -> {
                    progressBarId.visibility = View.GONE
                    it.data?.let {myPosts -> renderList(myPosts) }
                    myRecyclerViewId.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    progressBarId.visibility = View.GONE
                    networkErrorMsgId.visibility = View.VISIBLE
                    Toast.makeText(this, "${it.exception.message}",Toast.LENGTH_LONG).show()
                }

            }

        })


    }

    private fun renderList(myPosts:List<POST>){
        adapter.submitList(myPosts)
    }

}
