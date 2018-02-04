package com.nicholasdoglio.notes

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import com.nicholasdoglio.notes.di.DaggerAppComponent
import com.nicholasdoglio.notes.util.ReleaseTree
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Nicholas Doglio
 */
class NotesApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initLeakCanary()
        initDebugTools()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) { //init all debug tools
            initStrictMode()
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun initStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
    }
}