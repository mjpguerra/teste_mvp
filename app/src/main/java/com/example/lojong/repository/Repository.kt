package com.example.lojong.repository

import android.content.Context
import com.example.lojong.repository.remote.RemoteFactory
import com.example.lojong.repository.remote.RemoteRepository

class Repository(applicationContext: Context) : RepositoryFactory {
    override val remote: RemoteFactory = RemoteRepository()
}