package com.test.enbdtest.ui.di;

import com.test.enbdtest.di.scopes.FragmentScoped;
import com.test.enbdtest.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This Class {@linkplain MainActivityBuilderModule} is responsible for for android injection
 * for the activity with in the application to avoid the seprate injection in each activity
 *
 * {@linkplain dagger.android.AndroidInjection}
 *
 * {@link MainViewModelModule} can be access from Auth Activity
 * only so it is the concept of sub-modules
 *
 */
@Module
public abstract class MainActivityBuilderModule {

    /**
     * Automatically create sub-component
     *
     * @return
     */
    @FragmentScoped
    @ContributesAndroidInjector(
            modules = { MainViewModelModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
