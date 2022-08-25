package com.example.lokalise.test

import android.app.Application
import com.lokalise.sdk.Lokalise

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Lokalise.init(this, "", "")
        Lokalise.updateTranslations();
    }
}