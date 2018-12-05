package io.caster.simplemvp

import android.app.Application

class MvpApplication : Application() {
    var component: AppComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}