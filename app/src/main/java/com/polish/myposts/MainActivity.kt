package com.polish.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polish.myposts.viewModel.POSTViewModel

class MainActivity : AppCompatActivity() {

   lateinit var viewButton:Button
    lateinit var viewModel: POSTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(POSTViewModel::class.java)

        viewButton = findViewById(R.id.button)

        viewButton.setOnClickListener {

            viewModel.viewMyPostHere()

        }


    }
}
