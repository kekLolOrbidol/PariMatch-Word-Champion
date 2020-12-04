package me.navid.scrambilo.ui.hints

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.navid.scrambilo.di.FragmentScoped

@Module
internal abstract class HintModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeHintsFragment(): HintsFragment
}