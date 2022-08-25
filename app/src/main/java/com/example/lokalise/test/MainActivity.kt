package com.example.lokalise.test

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import com.example.lokalise.test.ui.main.MainFragment
import com.lokalise.sdk.Lokalise
import com.lokalise.sdk.LokaliseContextWrapper


class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        // Inject the Lokalise SDK into the activity context
        super.attachBaseContext(LokaliseContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val locale = ConfigurationCompat.getLocales(newConfig)[0] ?: return

        Lokalise.setLocale(
            context = this,
            language = locale.language,
            country = locale.country,
            variant = locale.variant
        )
        Lokalise.updateTranslations()
    }
}