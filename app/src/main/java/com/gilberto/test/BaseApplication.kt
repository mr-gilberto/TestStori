package com.gilberto.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication @Inject constructor() : Application() {


    override fun onCreate() {
        super.onCreate()

        application = this
    }

    companion object {
        lateinit var application: Application
    }
}
