package com.example.miraclegame.core.extentions

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.showToastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}