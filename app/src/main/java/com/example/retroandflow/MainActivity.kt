package com.example.retroandflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)

        viewModel.getNewComment(5)
        var s=CommentViewModel().getNewComment(5)
        lifecycleScope.launch {
            viewModel.commentstate.collect {
                when(it.status){
                    Status.SUCCESS->{
                        Log.d("data1","${it.data}")
                    }
                    Status.LOADING->{
                        Log.d("data2","${it.status}")
                    }
                    Status.ERROR->{
                        Log.d("data3","${it.message  }")

                    }
                }
            }
        }
    }
}