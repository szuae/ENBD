package com.test.enbdtest.di
import android.content.Context
import androidx.room.Room
import com.test.data.api.PixabayRepoApi
import com.test.data.db.PixbayDataBase
import com.test.data.util.NetworkUtil
import com.test.enbdtest.PixabayApplication
import com.test.enbdtest.di.scopes.AppScoped
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * [PixBayModule] class is responsible for providing application level dependencies
 * Anotated with singleton annotation to tell dagger these dependencies also exists
 * when [AppComponent] alive and destroy these dependencies when [AppComponent] destroy
 */
@Module
class PixBayModule (private val application: PixabayApplication) {

    @AppScoped
    @Provides
    fun providePixbayDataBase(): PixbayDataBase {
       return Room.databaseBuilder(
            application,
            PixbayDataBase::class.java,
            "GitRepoDatabase"
        ).build()
    }

    @AppScoped
    @Provides
    internal fun privideNetworkUtil(): NetworkUtil {
        return NetworkUtil(application)
    }
}