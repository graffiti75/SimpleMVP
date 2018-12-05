package io.caster.simplemvp

import javax.inject.Singleton

import dagger.Component
import io.caster.simplemvp.view.fragment.UserFragment

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(target: UserFragment)
}