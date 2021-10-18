package com.rommansabbir.commander.exceptions

class NotInitializedException(override val message : String = "Command Controller: Not initialized properly"): Throwable(message) {
}