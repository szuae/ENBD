package com.test.enbdtest

import com.test.enbdtest.di.DaggerAppComponent
import com.test.enbdtest.di.PixBayModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * The Base Application which will live entire lifecycle of an application.
 *
 * I want to make our AppComponent live in the entire lifecycler of an
 * application so we can instantiate it on applicationInjection
 * overrided method which is comming from [DaggerApplication]
 */
class PixabayApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this)
            .pixBayModule(PixBayModule(this)).build()
    }
}