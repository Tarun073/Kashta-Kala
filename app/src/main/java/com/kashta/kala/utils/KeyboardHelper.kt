package com.kashta.kala.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyboardHelper {
    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus ?: View(activity)
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}