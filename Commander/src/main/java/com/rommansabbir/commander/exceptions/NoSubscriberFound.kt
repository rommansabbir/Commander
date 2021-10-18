package com.rommansabbir.commander.exceptions

class NoSubscriberFound(override val message: String = "No subscriber found with the subscriptionId.") :
    Throwable(message)