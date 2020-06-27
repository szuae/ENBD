package com.test.enbdtest.di

import com.test.enbdtest.PixabayApplication
import com.test.enbdtest.di.scopes.AppScoped
import com.test.enbdtest.ui.di.MainActivityBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Component is a graph. We build a component. Component will provide injected instances by using modules.
 * Extends appcomponent with [AndroidInjector] to avoid old way of injection application
 *
 * <code>
 *     fun inject(application: BaseApplication)
 * </code>
 *
 * AppComponent is act as a server whereas, [Application] act as a client.
 * Dagger interaction is like client-server interaction
 *
 * Anotated with [Singleton] Scope to tell dagger to keep it in the memory while application exists
 * and destroy it when application destroy
 */
@AppScoped
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    PixBayModule::class,
    ViewModelFactoryModule::class,
    MainActivityBuilderModule::class ])
interface AppComponent : AndroidInjector<PixabayApplication> {

    @Component.Builder
    interface Builder {

        /**
         * [BindsInstance] annotation is used for, if you want to bind particular object or instance
         * of an object through the time of component construction
         */
        @BindsInstance
        fun application(application: PixabayApplication) : Builder

        fun pixBayModule(pixbayModule: PixBayModule): Builder

        fun build() : AppComponent
    }

}
