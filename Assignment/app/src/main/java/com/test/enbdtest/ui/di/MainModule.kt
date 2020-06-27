package com.test.enbdtest.ui.di

import com.test.data.api.PixabayRepoApi
import com.test.data.db.PixbayDataBase
import com.test.data.repository.PixabayRepoCache
import com.test.data.repository.PixabayRepoImpl
import com.test.data.repository.PixabayRepoRemote
import com.test.data.util.NetworkUtil
import com.test.domain.repository.PixabayRepository
import com.test.domain.usecases.PixabayRepoUsecase
import com.test.domain.usecases.PixabayRepoUsecaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    internal fun providePixabayRepoApi(retrofit: Retrofit): PixabayRepoApi {
        return retrofit.create(PixabayRepoApi::class.java)
    }

    @Provides
    internal fun providePixabayRepoCache(dataBase: PixbayDataBase): PixabayRepoCache {
        return PixabayRepoCache(dataBase)
    }

    @Provides
    internal fun providePixabayRepoRemote(api: PixabayRepoApi): PixabayRepoRemote {
        return PixabayRepoRemote(api)
    }

    @Provides
    internal fun providePixabayRepository(remote: PixabayRepoRemote, cache: PixabayRepoCache, networkState : NetworkUtil): PixabayRepository {
        return PixabayRepoImpl(remote,cache,networkState)
    }

    @Provides
    internal fun providePixabayRepoUseCase(squireRepository: PixabayRepository): PixabayRepoUsecase {
        return PixabayRepoUsecaseImpl(squireRepository)
    }

}