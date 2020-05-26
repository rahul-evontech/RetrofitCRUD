package com.smartherd.retrofitjosn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smartherd.retrofitjosn.data.placeholderItem
import com.smartherd.retrofitjosn.repository.PlaceholderRepository
import com.smartherd.retrofitjosn.viewModel.MainViewModel
import com.smartherd.retrofitjosn.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = MyApi()
        val repository = PlaceholderRepository(api)
        val factory = MainViewModelFactory(repository)

        val viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)

//        viewModel.getMessages()
//        viewModel.getMessagesId(1)

        viewModel._messages.observe(this, Observer{
            Log.e("hello","running")
            Log.i("hello","Messages:  ${it.first()}")
            Toast.makeText(this,"Toast : ${it.first()}",Toast.LENGTH_LONG).show()
        })

        viewModel._messageId.observe(this, Observer{
            Log.e("hello","running messageId")
            Log.i("hello","Messages by Id:  ${it}")
            Toast.makeText(this,"Toast : ${it.first()}",Toast.LENGTH_LONG).show()
        })


//        viewModel.getPost("abc","Strive to be the best",101)

        viewModel._post.observe(this, Observer {
            Log.i("hello","Messages: ${it}")
        })

        val newUser = placeholderItem(101,1,"Messi","When you fall down get back up")

//        viewModel.getPostBody(newUser)

        viewModel._postId.observe(this, Observer {
            Log.i("hello","Messages in postId: ${it}")
        })

//        viewModel.getPut(101,1,"abc","Strive to be the best")

        viewModel.put.observe(this,Observer{
            Log.e("hello","running put ")
            Log.i("hello","Messages by Id:  ${it}")
            Toast.makeText(this,"Toast : ${it}",Toast.LENGTH_LONG).show()
        })

        viewModel.getPatch(101,"Never BAck Down")

        viewModel.patch.observe(this, Observer {
            Log.e("hello","running patch ")
            Log.i("hello","Messages by Id:  ${it}")
            Toast.makeText(this,"Toast : ${it}",Toast.LENGTH_LONG).show()
        })

//        viewModel.getDelete(1)

        viewModel.delete.observe(this, Observer {
            Log.e("hello","running delete ")
            Log.i("hello","Messages by Id:  ${it}")
            Toast.makeText(this,"Toast : ${it}",Toast.LENGTH_LONG).show()
        })
    }
}
