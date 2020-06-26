package com.test.enbdtest.ui.main

import androidx.lifecycle.MutableLiveData
import com.test.domain.usecases.PixabayRepoUsecase
import com.test.enbdtest.BaseViewModel
import com.test.enbdtest.entity.Data
import com.test.enbdtest.entity.PixabayRepo
import com.test.enbdtest.mapper.DomainToPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private var useCase : PixabayRepoUsecase) : BaseViewModel(){


    private val mapper  = DomainToPresentationMapper()
    private var dataList = MutableLiveData<Data<List<PixabayRepo>>>()

    fun getPixabayRepos(fetchFromServer: Boolean) {
        if(dataList.value != null && !fetchFromServer) {
            dataList.postValue(dataList.value)
        }else {
            launch {
                val deviceInfo = useCase.getPixabayRepos()
                deviceInfo.consumeEach { response ->
                    val mappedResponse = mapper.mapTo(response)
                    withContext(Dispatchers.Main) {
                        dataList.postValue(mappedResponse)
                    }
                }
            }
        }
    }
    fun getData() =dataList
}
