package com.test.enbdtest.ui.di

import com.test.data.api.PixabayRepoApi
import com.test.data.repository.PixabayRepoImpl
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
    internal fun providePixabayRepository(api: PixabayRepoApi): PixabayRepository {
        return PixabayRepoImpl(api)
    }

    @Provides
    internal fun providePixabayRepoUseCase(squireRepository: PixabayRepository): PixabayRepoUsecase {
        return PixabayRepoUsecaseImpl(squireRepository)
    }

}