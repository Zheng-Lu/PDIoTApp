package com.specknet.pdiotapp.login

import com.specknet.pdiotapp.login.utils.InputValidator
import com.specknet.pdiotapp.login.database.UserDatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.specknet.pdiotapp.MainActivity

import com.specknet.pdiotapp.R


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@LoginActivity

    private lateinit var textInputLayoutEmail: LinearLayout
    private lateinit var textInputLayoutPassword: LinearLayout

    private lateinit var textInputEditTextEmail: EditText
    private lateinit var textInputEditTextPassword: EditText

    private lateinit var ButtonLogin: Button

    private lateinit var textViewLinkRegister: Button
    private lateinit var textViewLinkForgot: Button

    private lateinit var inputValidator: InputValidator
    private lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_page)

        // hiding the action bar
        supportActionBar!!.hide()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()
    }

    private fun initViews() {

        textInputLayoutEmail = findViewById<LinearLayout>(R.id.L_Email)
        textInputLayoutPassword = findViewById<LinearLayout>(R.id.L_Password)

        textInputEditTextEmail = findViewById<EditText>(R.id.L_EmailInput)
        textInputEditTextPassword = findViewById<EditText>(R.id.L_PasswordInput)

        ButtonLogin = findViewById<Button>(R.id.LoginSubmit)

        textViewLinkRegister = findViewById<Button>(R.id.SignupButton)
        textViewLinkForgot = findViewById<Button>(R.id.ForgotButton)

    }

    private fun initListeners() {

        ButtonLogin.setOnClickListener(this)
        textViewLinkRegister.setOnClickListener(this)
        textViewLinkForgot.setOnClickListener(this)
    }


    private fun initObjects() {
        userDatabase = UserDatabase(activity)
        inputValidator = InputValidator(activity)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.login_btn -> verifyFromSQLite()
            R.id.register_btn -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
            R.id.forgotPassword -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, ForgetPasswordActivity::class.java)
                startActivity(intentRegister)
            }
        }
    }


    private fun verifyFromSQLite() {

        if (!inputValidator.isInputEditTextFilled(textInputEditTextEmail)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Wrong e-mail or password", Toast.LENGTH_SHORT).show()
            return
        }
        if (!inputValidator.isInputEditTextEmail(textInputEditTextEmail)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Wrong e-mail or password", Toast.LENGTH_SHORT).show()
            return
        }
        if (!inputValidator.isInputEditTextFilled(textInputEditTextPassword)) {
            hideKeyboardFrom(textInputLayoutEmail)
            Toast.makeText(this, "Wrong e-mail or password", Toast.LENGTH_SHORT).show()
            return
        }

        if (userDatabase.checkUser(textInputEditTextEmail.text.toString().trim { it <= ' ' },
                textInputEditTextPassword.text.toString().trim { it <= ' ' })) {

            val user = userDatabase.getUser(textInputEditTextEmail.text.toString().trim { it <= ' ' })
            val name = user.username
            val email = user.email

            //databaseHelper.updateUserSesh(user, true)
            val accountsIntent = Intent(activity, MainActivity::class.java)
            accountsIntent.putExtra("username", name)
            accountsIntent.putExtra("email", email)
            emptyInputEditText()
            startActivity(accountsIntent)


        } else {

            // Snack Bar to show success message that record is wrong
            //Snackbar.make(nestedScrollView!!, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show()
            Toast.makeText(this, "Wrong e-mail or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun emptyInputEditText() {
        textInputEditTextEmail.text = null
        textInputEditTextPassword.text = null
    }

    private fun hideKeyboardFrom(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}