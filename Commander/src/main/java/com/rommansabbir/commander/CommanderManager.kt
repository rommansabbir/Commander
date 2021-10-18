package com.rommansabbir.commander

import android.util.Log

import com.rommansabbir.commander.exceptions.NotInitializedException

/**
 * This is the main access point for client to initialize the [Commander] or return the installed [Commander] instance.
 */
object CommanderManager {
    @Volatile
    private var INSTANCE: CommanderImpl? = null

    fun initialize() {
        if (INSTANCE == null) {
            CommanderImpl.clear()
            INSTANCE = CommanderImpl.getInstance()
        } else {
            Log.e(
                "CommandController",
                "Instance already initialized, Skipping new initialization."
            )
        }
    }

    fun getInstance(): Commander =
        if (INSTANCE == null) throw NotInitializedException() else INSTANCE!!

}