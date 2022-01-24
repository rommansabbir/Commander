package com.rommansabbir.commander


/**
 * This is the main entry for client to register, unregister or broadcast command to the subscriber's.
 */
interface Commander {
    fun register(subscriptionId: String, commandListener: Listener)
    fun unregister(subscriptionId: String): Boolean
    fun broadcastCommand(command: Command, ignoreNoSubscriberFoundException : Boolean = true)

    interface Listener {
        fun receiveCommand(command: Command)
    }
}

