package ru.rsue.borisov.thecloudmultiplatform.shared


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
