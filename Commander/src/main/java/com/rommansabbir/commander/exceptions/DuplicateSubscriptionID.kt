package com.rommansabbir.commander.exceptions

class DuplicateSubscriptionID(override val message : String = "Duplicate id found. SubscriptionId must be an unique identifier.") : Throwable(message)