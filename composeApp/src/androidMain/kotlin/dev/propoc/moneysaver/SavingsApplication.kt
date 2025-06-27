package dev.propoc.moneysaver

import android.app.Application
import dev.propoc.moneysaver.di.initKoin
import org.koin.android.ext.koin.androidContext

class SavingsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SavingsApplication)
        }
    }
}