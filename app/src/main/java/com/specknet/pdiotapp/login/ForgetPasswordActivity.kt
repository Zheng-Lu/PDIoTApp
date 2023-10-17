package com.specknet.pdiotapp.login

import android.app.Activity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import android.view.inputmethod.InputMethodManager

import com.specknet.pdiotapp.R
import com.specknet.pdiotapp.login.utils.InputValidator


class ForgetPasswordActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@ForgetPasswordActivity

    private lateinit var textInputLayoutEmail: LinearLayout
    private lateinit var textInputLayoutPassword: LinearLayout
    private lateinit var textInputLayoutConfirmPassword: LinearLayout

    private lateinit var textInputEditTextEmail: EditText
    private lateinit var textInputEditTextPassword: EditText
    private lateinit var textInputEditTextConfirmPassword: EditText

    private lateinit var appCompatButtonResetPass: Button
    private lateinit var appCompatTextViewLoginLink: Button

    private lateinit var inputValidator: InputValidator
    private lateinit var userDatabase: userDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.forgot_draft)

        // hiding the action bar
        supportActionBar!!.hide()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {

        textInputLayoutEmail = findViewById<LinearLayout>(R.id.F_Email)
        textInputLayoutPassword = findViewById<LinearLayout>(R.id.F_Password)
        textInputLayoutConfirmPassword = findViewById<LinearLayout>(R.id.F_ConfPassword)

        textInputEditTextEmail = findViewById<EditText>(R.id.F_EmailInput)
        textInputEditTextPassword = findViewById<EditText>(R.id.F_PasswordInput)
        textInputEditTextConfirmPassword = findViewById<EditText>(R.id.F_ConfPasswordInput)

        appCompatButtonResetPass = findViewById<Button>(R.id.ForgotSubmit)

        appCompatTextViewLoginLink = findViewById<Button>(R.id.RememberedButton)

    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonResetPass.setOnClickListener(this)
        appCompatTextViewLoginLink.setOnClickListener(this)

    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        inputValidator = InputValidator(activity)
        userDatabase = userDatabase(activity)


    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {

            R.id.ForgotSubmit -> postDataToSQLite()

            R.id.RememberedButton -> finish()
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private fun postDataToSQLite() {

        if (!inputValidator.isInputEditTextFilled(textInputEditTextEmail)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return
        }
        if (!inputValidator.isInputEditTextEmail(textInputEditTextEmail)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            return
        }
        if (!inputValidator.isInputEditTextFilled(textInputEditTextPassword)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Please enter your new password", Toast.LENGTH_SHORT).show()
            return
        }
        if (!inputValidator.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        if (userDatabase.checkUser(textInputEditTextEmail.text.toString().trim())) {

            val user = userDatabase.getUser(textInputEditTextEmail.text.toString().trim())
            userDatabase.updateUserPass(user, textInputEditTextPassword.text.toString().trim())

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(this, "The password was successfully changed. Please go back to login page", Toast.LENGTH_SHORT).show()
            emptyInputEditText()


        } else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(this, "This email is not registered", Toast.LENGTH_SHORT).show()
        }


    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextEmail.text = null
        textInputEditTextPassword.text = null
        textInputEditTextConfirmPassword.text = null
    }

    /**
     * This method is to hide keyboard away from screen
     * @param view
     */
    private fun hideKeyboardFrom(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}