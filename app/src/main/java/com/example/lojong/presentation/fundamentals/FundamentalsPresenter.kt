package com.example.lojong.presentation.fundamentals

import androidx.lifecycle.MutableLiveData
import com.example.lojong.base.extensions.launchIO
import com.example.lojong.repository.local.fundamentals.ExampleData
import com.example.lojong.repository.local.fundamentals.ExampleRepositoryMock

class FundamentalsPresenter(override var view: FundamentalsContract.View, private val repository: ExampleRepositoryMock) : FundamentalsContract.Presenter {

    var response = MutableLiveData<ExampleData>()

    override fun init() {
        loadData()
    }

    override fun loadData() {
        view.handleMessageVisibility(shouldShow = false)
        launchIO {
            response.postValue(repository.getExample())
        }
        view.handleMessageVisibility(shouldShow = true)
    }

    override fun observeForExampleData(): MutableLiveData<ExampleData> {
        return response
    }
}