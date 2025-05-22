package com.sidor.procuts.ui

import android.content.Context
import android.widget.Toast

class ToastNotifier(private val context: Context) {
    fun show(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

    fun showLong(message: String) {
        show(message, Toast.LENGTH_LONG)
    }
}