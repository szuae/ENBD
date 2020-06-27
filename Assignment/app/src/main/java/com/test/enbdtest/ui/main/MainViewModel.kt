package com.test.enbdtest.ui.main

import androidx.lifecycle.MutableLiveData
import com.test.domain.usecases.PixabayRepoUsecase
import com.test.enbdtest.BaseViewModel
import com.test.enbdtest.Keys
import com.test.enbdtest.entity.Data
import com.test.enbdtest.entity.PixabayRepo
import com.test.enbdtest.mapper.PresentationMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private var useCase : PixabayRepoUsecase) : BaseViewModel(){

    private val mapper  = PresentationMapper()
    private var dataList = MutableLiveData<Data<List<PixabayRepo>>>()
    private var currentPageNumber = 1
    private var currentQuerry = ""
    var isNextPageRequest = false

    fun getPixabayRepos(searchParam: String, pageNo: Int) {
        currentQuerry = searchParam
        currentPageNumber = pageNo
        isNextPageRequest = false
        launch {
            useCase.getPixabayRepos(CoroutineScope(coroutineContext),Keys.apiKey(),searchParam, currentPageNumber).consumeEach { response ->
                val mappedResponse = mapper.mapTo(response)
                withContext(Dispatchers.Main) {
                    dataList.postValue(mappedResponse)
                }
            }
        }
    }

    fun getPixabayReposNext() {
        isNextPageRequest = true
        currentPageNumber++
        getPixabayRepos(currentQuerry,currentPageNumber)
    }
    fun getData() =dataList
}