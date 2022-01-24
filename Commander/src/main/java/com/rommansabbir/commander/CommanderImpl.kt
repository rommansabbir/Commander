package com.rommansabbir.commander

import android.util.Log
import com.rommansabbir.commander.exceptions.DuplicateSubscriptionID
import com.rommansabbir.commander.exceptions.NoSubscriberFound

class CommanderImpl private constructor() :
    Commander {
    companion object {
        fun getInstance() = CommanderImpl()
        private val LIST: HashMap<String, Commander.Listener> = HashMap()
        fun clear() = LIST.clear()
    }

    override fun register(
        subscriptionId: String,
        commandListener: Commander.Listener,
    ) {
        synchronized(subscriptionId) {
            logThis("register :: $subscriptionId")
            try {
                if (LIST[subscriptionId] == null) LIST[subscriptionId] =
                    commandListener else throw DuplicateSubscriptionID()
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override fun unregister(subscriptionId: String): Boolean {
        synchronized(subscriptionId) {
            logThis("unregister :: $subscriptionId")
            try {
                if (LIST[subscriptionId] == null) return false
                LIST.remove(subscriptionId)
                return true
            } catch (e: Exception) {
                throw e
            }
        }
    }

    override fun broadcastCommand(command: Command, ignoreNoSubscriberFoundException: Boolean) {
        synchronized(command) {
            logThis("broadcastCommand :: $command")
            try {
                if (LIST[command.receiverSubscriptionId] == null) {
                    if (!ignoreNoSubscriberFoundException) {
                        throw NoSubscriberFound()
                    } else {
                        logThis("broadcastCommand :: Ignoring exception")
                    }
                } else LIST[command.receiverSubscriptionId]?.receiveCommand(command)

            } catch (e: Exception) {
                if (!ignoreNoSubscriberFoundException) {
                    throw e
                } else {
                    logThis("broadcastCommand :: Ignoring exception")
                }
            }
        }
    }

    private fun logThis(log: String) {
        if (BuildConfig.DEBUG) {
            Log.d("Commander", log)
        }
    }
}


