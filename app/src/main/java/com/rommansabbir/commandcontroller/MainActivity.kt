package com.rommansabbir.commandcontroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rommansabbir.commander.Command
import com.rommansabbir.commander.Commander
import com.rommansabbir.commander.CommanderManager

class MainActivity : AppCompatActivity() {
    companion object {
        const val SubscriptionId: String = "MainActivity"
    }

    private lateinit var class1: Class1
    private lateinit var class2: Class2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CommanderManager.getInstance().register(SubscriptionId, listener)

        class1 = Class1()
        class2 = Class2()

        CommanderManager.getInstance().broadcastCommand(
            Command(
                "Init Command",
                mutableListOf<String>(),
                Class1.SubscriptionId
            )
        )

        CommanderManager.initialize()
    }

    private val listener = object : Commander.Listener {
        override fun receiveCommand(command: Command) {
            println(command.toString())
        }
    }
}

class Class1 : Commander.Listener {
    init {
        CommanderManager.getInstance().register(SubscriptionId, this)
    }

    companion object {
        const val SubscriptionId: String = "Class1"
    }

    private fun sendCommand(command: Command) {
        CommanderManager.getInstance().broadcastCommand(
            Command(
                "DummyCommandFromClass1ToClass2",
                command,
                Class2.SubscriptionId
            )
        )
    }

    override fun receiveCommand(command: Command) {
        println("Class 1: Command received")
        sendCommand(command)
    }
}

class Class2 : Commander.Listener {
    init {
        CommanderManager.getInstance().register(SubscriptionId, this)
    }

    companion object {
        const val SubscriptionId: String = "Class2"
    }

    override fun receiveCommand(command: Command) {
        println("Class 2: Command received")
        sendCommand(command)
    }

    private fun sendCommand(command: Command) {
        CommanderManager.getInstance().broadcastCommand(
            Command(
                "Class2 to MainActivity",
                command,
                MainActivity.SubscriptionId
            )
        )
    }
}