[![Release](https://jitpack.io/v/jitpack/android-example.svg)](https://jitpack.io/#rommansabbir/StoreX)
# Commander
Handy library to send & receive command with payload between subscribers for Android.

## Features
* Subscription based
* No dependency on Framework
* Lightweight

## Documentation

### Installation

---
Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.rommansabbir:Commander:Tag'
}
```

---

### Version available

| Latest Releases
| ------------- |
| 1.3.0         |

---

## Initialize
````
CommanderManager.initialize()
````
Note: You can initialize `Commander` from anywhere in application scope. But it's recommanded to `initialize` `Commander` from your application class. 

---
### Calling `CommanderManager.initialize()` several time from anywhere will be ignored if the instance is already initialized.
---

## How To Access
````
CommanderManager.getInstance()
````
which return an instace of `Commander`. 

Note: Before accessing `Commander` you must `initialize` `Commander` properly or else it will throw `NotInitializedException` exception.

----

# Available methods
- **register(subscriptionId: String, commandListener: Listener)**
- **unregister(subscriptionId: String): Boolean**
- **broadcastCommand(command: Command)**
- **receiveCommand(command: Command)** [`Commander.Listener`]

# Usages

## How to register/unregister?

To register to `Commander`, you must initialize the `Commander.Listener` to get broadcasted command from other susbcribers.

Example:
`````
private val listener = object : Commander.Listener {
    override fun receiveCommand(command: Command) {
        println(command.toString())
    }
}

//Register as a subscriber.
CommanderManager.getInstance().register(SubscriptionId, listener)
`````
Note: `SubscriptionId` must be an unique identifier. If you try to register with an duplicate `SubscriptionId`, it will throw an `DuplicateSubscriptionID` exception.

---

## `SubscriptionId` is the public unique identifer to communicate/send command between subscribers.
---


## How to Broadcast a command using Commander?
Get the instance through `CommanderManager.getInstance()`
````
CommanderManager.getInstance().broadcastCommand(
        Command(
            "Init Command",
            mutableListOf<String>(),
            Class1.SubscriptionId
        )
    )
````
Note: If you try to send command to a not registered subscriber , then it will throw `NoSubscriberFound` exception.

---
## What is command?. `Command` is a simple data class that take action, payload and subscriptionId as the params & pass the same instance to the respective subscriber. `SubscriberId` is the main key point to identify to whom the command will be sent.
---

## How to receive command?
Register as a subscriber and the command will be passed to the `Commander.Listener`'s `onReceived()` callback method.

````
private val listener = object : Commander.Listener {
    override fun receiveCommand(command: Command) {
        //Broadcasted command would be received here if the `SubscriptionId` matched properly.
    }
}

````

## For detail implementation, check the demo application. Happy Coding...

----

### Contact me
[Portfolio](https://www.rommansabbir.com/) | [LinkedIn](https://www.linkedin.com/in/rommansabbir/) | [Twitter](https://www.twitter.com/itzrommansabbir/) | [Facebook](https://www.facebook.com/itzrommansabbir/)

### License

---
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

````
Copyright (C) 2021 Romman Sabbir

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

		http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````


