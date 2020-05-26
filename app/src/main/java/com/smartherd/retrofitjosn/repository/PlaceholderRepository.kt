package com.smartherd.retrofitjosn.repository

import com.smartherd.retrofitjosn.MyApi
import com.smartherd.retrofitjosn.data.placeholderItem

class PlaceholderRepository(
    val api: MyApi
) {

    suspend fun getMessages() = api.getMessages()

    suspend fun getMessagesId(postId: Int) = api.getMessagesId(postId)

    suspend fun getPosts(title: String,body: String,userId: Int) = api.getPostsUrlEncoded(title,body,userId)

    suspend fun getPostBody(holder: placeholderItem) = api.getPostBody(holder)

    suspend fun getPut(userId: Int,id: Int,title:String,body: String) = api.getPut(userId, id, title, body)

    suspend fun getPatch(userId: Int,title: String) = api.getPatch(userId, title)

    suspend fun getDelete(userId: Int) = api.getDelete(userId)
}
