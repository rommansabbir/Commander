package com.rommansabbir.commandcontroller

import android.app.Application
import com.rommansabbir.commander.CommanderManager

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        com.rommansabbir.commander.CommanderManager.initialize()
    }
}