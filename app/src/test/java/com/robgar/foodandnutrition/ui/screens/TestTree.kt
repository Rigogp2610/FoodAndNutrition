package com.robgar.foodandnutrition.ui.screens

import timber.log.Timber

class TestTree : Timber.Tree() {
    val logs = mutableListOf<String>()

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        logs.add("[$tag] $message")
    }
}