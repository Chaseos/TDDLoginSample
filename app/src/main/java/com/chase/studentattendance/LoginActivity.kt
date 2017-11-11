package com.chase.studentattendance

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.chase.studentattendance.login.LoginPresenter
import com.chase.studentattendance.login.LoginView

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var editUserName : EditText
    private lateinit var editPassword : EditText
    private lateinit var buttonLogin : Button
    private lateinit var loginPresenter : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializePresenter()
        initializeViews()
    }

    private fun initializePresenter() {loginPresenter = LoginPresenter(this)
    }

    private fun initializeViews() {
        editUserName = findViewById(R.id.username_textinput)
        editPassword = findViewById(R.id.password_textinput)
        buttonLogin = findViewById(R.id.login_button)
        buttonLogin.setOnClickListener{
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(currentFocus.applicationWindowToken, 0)
            loginPresenter.doLogin(editUserName.text.toString().trim(),
                    editPassword.text.toString().trim())
        }
    }

    override fun showErrorMessageForUsernamePassword() {
        Snackbar.make(editPassword, "Please check Username or Password.", Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorMessageForMaxLoginAttempt() {
        Snackbar.make(buttonLogin, "You have exceeded MAX attempts.", Snackbar.LENGTH_LONG).show()
    }

    override fun showLoginSuccessMessage() {
        Snackbar.make(buttonLogin, "Login successful.", Snackbar.LENGTH_LONG).show()
    }
}
