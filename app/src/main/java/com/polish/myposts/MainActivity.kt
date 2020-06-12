package com.polish.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
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
    // activity
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(POSTViewModel::class.java)

//        val binding = ActivityMainBinding.inflate(layoutInflater)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.viewButtonId.setOnClickListener {
//
//            viewModel.viewMyPostHere()
//
//        }
        // Allows Data Binding to observe LiveData with the Lifecycle of this activity
        binding.lifecycleOwner = this

        // Giving the binding access to the POSTViewModel
        binding.postViewModel = viewModel

        button = findViewById(R.id.view_buttonId)
        button.setOnClickListener {
            viewModel.viewMyPostHere()
        }


//        val recyclerView = binding.myRecyclerViewId
        val recyclerView:RecyclerView = findViewById(R.id.myRecyclerViewId)
        recyclerView.layoutManager = LinearLayoutManager(this)

         adapter = POSTAdapter(
             POSTAdapter.OnClickListener { postItem ->
                 Toast.makeText(this, "${postItem}", Toast.LENGTH_LONG).show()

                 viewModel.onPostClicked(postItem)

             }
         )
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
//                    progressBarId.visibility = View.GONE
                    it.data?.let {
                        adapter.addHeaderAndSubmitList(it)
                        Log.i(TAG, "What is inside observer result:$it")
                    }
                    myRecyclerViewId.visibility = View.VISIBLE
                }
                is Result.Error -> {
//                    progressBarId.visibility = View.GONE
//                    networkErrorMsgId.visibility = View.VISIBLE
                    myRecyclerViewId.visibility = View.GONE
                    Toast.makeText(this, "${it.exception.message}",Toast.LENGTH_LONG).show()
                }

            }

        })

        // this code is the observe the click, that is observe the navigateToDetail
        viewModel.navigateToPostDetail.observe(this, Observer { postItem ->
            postItem?.let {
                // normally to use a navigation controller but am not using a navigation controller
                // i will use intent

                /*
                if am to use navigation controller it will be like this

                this.findNavController().naviate(
                MainActivityDirectons.actionMainActivityToDetailActivity(postItem)
                )
                viewModel.onPostDetailNAvigated()

                 */

            }
        })


    }

//    private fun renderList(itemPost:List<POST>){
//        adapter.addHeaderAndSubmitList(itemPost)
//    }

}
