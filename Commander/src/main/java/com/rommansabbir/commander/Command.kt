package com.rommansabbir.commander

/**
 * This class represent the command.
 *
 * @param command, express that client should implement logic based on this command
 * @param params, payload that need to be sent
 * @param receiverSubscriptionId, unique identifier for subscription
 */
data class Command(val command: String, val params: Any, val receiverSubscriptionId: String)