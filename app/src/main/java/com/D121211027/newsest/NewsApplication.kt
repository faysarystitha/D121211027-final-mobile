package com.D121211027.newsest

import android.app.Application
import com.D121211027.newsest.data.AppContainer
import com.D121211027.newsest.data.DefaultAppContainer

class NewsApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}