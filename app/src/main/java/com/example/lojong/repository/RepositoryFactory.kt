package com.example.lojong.repository

import com.example.lojong.repository.remote.RemoteFactory


interface RepositoryFactory {
    val remote: RemoteFactory
}