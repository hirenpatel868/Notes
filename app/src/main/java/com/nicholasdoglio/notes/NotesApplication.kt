/*
 * MIT License
 *
 * Copyright (c) 2020 Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nicholasdoglio.notes

import android.app.Application
import android.os.StrictMode
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nicholasdoglio.notes.di.AppComponent
import com.nicholasdoglio.notes.di.AppComponentProvider
import com.nicholasdoglio.notes.di.DaggerAppComponent
import com.nicholasdoglio.notes.features.daynight.NightMode
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

// TODO design inspiration https://material.io/design/material-studies/fortnightly.html#
// Standardize how I handle navigation (directions vs xml ids)
class NotesApplication : Application(), AppComponentProvider {

    private val dispatchers by lazy { component.dispatcherProvider }

    override val component: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(NightMode.FOLLOW_SYSTEM.value)
        AndroidThreeTen.init(this)
        initDebugTools()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            initStrictMode()
            initFlipper()
        }
    }

    private fun initStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }

    private fun initFlipper() {
        GlobalScope.launch(dispatchers.background) {
            SoLoader.init(this@NotesApplication, false)
            if (FlipperUtils.shouldEnableFlipper(this@NotesApplication)) {
                AndroidFlipperClient.getInstance(this@NotesApplication).apply {
                    addPlugin(
                        InspectorFlipperPlugin(
                            this@NotesApplication,
                            DescriptorMapping.withDefaults()
                        )
                    )
                    addPlugin(DatabasesFlipperPlugin(this@NotesApplication))
                    addPlugin(
                        SharedPreferencesFlipperPlugin(
                            this@NotesApplication,
                            NOTES_PREFERENCES
                        )
                    )
                }.start()
            }
        }
    }

    companion object {
        private const val NOTES_PREFERENCES = "com.nicholasdoglio.notes_preferences"
    }
}
