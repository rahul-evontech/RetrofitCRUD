package com.smartherd.retrofitjosn.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartherd.retrofitjosn.data.placeholderItem
import com.smartherd.retrofitjosn.repository.PlaceholderRepository
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: PlaceholderRepository
): ViewModel() {

    val _messages  = MutableLiveData<List<placeholderItem>>()
    val messages: LiveData<List<placeholderItem>>
    get() = _messages

    val _messageId = MutableLiveData<List<placeholderItem>>()
    val messageId : LiveData<List<placeholderItem>>
    get() = _messageId

    val _post = MutableLiveData<placeholderItem>()
    val post: LiveData<placeholderItem>
    get() = _post

    val _postId = MutableLiveData<placeholderItem>()
    val postId: LiveData<placeholderItem>
    get() = _postId

    val _put = MutableLiveData<placeholderItem>()
    val put: LiveData<placeholderItem>
        get() = _put

    val _patch = MutableLiveData<placeholderItem>()
    val patch: LiveData<placeholderItem>
        get() = _patch

    val _delete = MutableLiveData<placeholderItem>()
    val delete: LiveData<placeholderItem>
        get() = _delete

    fun getMessages() = viewModelScope.launch {
        val response = repository.getMessages()
        if(response.isSuccessful){
            _messages.value = response.body()
        }else{
            Log.e("error","Error in getMessages   : ${response.code()}")
        }
    }

    fun getMessagesId(postId: Int) = viewModelScope.launch {
        val response = repository.getMessagesId(postId)
        if(response.isSuccessful){
            _messageId.postValue(response.body())
        }else{
            Log.e("error","Error in getMessages by Id ${response.code()}")
        }
    }

    fun getPost(title: String, body: String, userId: Int) = viewModelScope.launch{
        val response = repository.getPosts(title,body,userId)
        if(response.isSuccessful){
            _post.postValue(response.body())
        }else{
            Log.e("error","Error in getPost: ${response.code()}")
        }
    }

    fun getPostBody(holder: placeholderItem) = viewModelScope.launch {
        val response = repository.getPostBody(holder)
        if(response.isSuccessful){
            _postId.postValue(response.body())
        }else{
            Log.e("error","Error in getPost: ${response.code()}")
        }
    }

    fun getPut(userId: Int,id: Int,title:String,body: String) = viewModelScope.launch {
        val response = repository.getPut(userId, id, title, body)
        if(response.isSuccessful){
            _postId.postValue(response.body())
        }else{
            Log.e("error","Error in getPost: ${response.code()}")
        }
    }

    fun getPatch(userId: Int,title: String) = viewModelScope.launch {
        val response = repository.getPatch(userId, title)
        if(response.isSuccessful){
            _patch.postValue(response.body())
        }else{
            Log.e("error","Error in getPost: ${response.code()}")
        }
    }

    fun getDelete(userId: Int) = viewModelScope.launch {
        val response = repository.getDelete(userId)
        if(response.isSuccessful){
            _delete.postValue(response.body())
        }else{
            Log.e("error","Error in getPost: ${response.code()}")
        }
    }

}