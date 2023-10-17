package com.specknet.pdiotapp.login.utils

import android.content.Context
import android.widget.*
import android.util.Patterns

class InputValidator(private val context: Context) {

    fun isInputEditTextFilled(textInputEditText: EditText): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty()) {
            return false
        }

        return true
    }

    fun isInputEditTextEmail(textInputEditText: EditText): Boolean {
        val value = textInputEditText.text.toString().trim()
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return false
        }
        return true
    }

    fun isInputEditTextMatches(textInputEditText1: EditText, textInputEditText2: EditText): Boolean {
        val value1 = textInputEditText1.text.toString().trim()
        val value2 = textInputEditText2.text.toString().trim()
        if (!value1.contentEquals(value2)) {
            return false
        }
        return true
    }
}