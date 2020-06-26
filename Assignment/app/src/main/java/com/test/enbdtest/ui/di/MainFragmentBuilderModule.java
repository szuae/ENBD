package com.test.enbdtest.ui.di;

import com.test.enbdtest.di.scopes.FragmentScoped;
import com.test.enbdtest.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This Class {@linkplain MainFragmentBuilderModule} is responsible for for android injection
 * for the activity with in the application to avoid the seprate injection in each activity
 *
 * {@linkplain dagger.android.AndroidInjection}
 *
 * {@link MainViewModelModule} can be access from Auth Activity
 * only so it is the concept of sub-modules
 *
 */
@Module
public abstract class MainFragmentBuilderModule {

    /**
     * Automatically create sub-component
     *
     * @return
     */
    @FragmentScoped
    @ContributesAndroidInjector(
            modules = { MainViewModelModule.class, MainModule.class}
    )
    abstract MainFragment contributeMainFragment();
}
