package me.navid.scrambilo.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.navid.scrambilo.ui.MainActivity
import me.navid.scrambilo.ui.game.GameModule
import me.navid.scrambilo.ui.hints.HintModule
import me.navid.scrambilo.ui.home.HomeModule

@Module
abstract class ActivityModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModule::class, GameModule::class, HintModule::class])
    internal abstract fun mainActivity(): MainActivity
}