package com.test.enbdtest.ui.di

import androidx.lifecycle.ViewModel
import com.test.enbdtest.di.scopes.ViewModelKey
import com.test.enbdtest.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    /**
     * Binds the  view model dependency with [ViewModelKey] to group similar [ViewModel]
     *
     * Under the hood it is providing MainViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel
}