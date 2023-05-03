package com.example.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.R
import com.example.ui.GuideActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        val username = findViewById<EditText>(R.id.login_username)
        val password = findViewById<EditText>(R.id.login_password)
        val login = findViewById<AppCompatButton>(R.id.login_login)
        val register = findViewById<AppCompatButton>(R.id.login_register)
        login.setOnClickListener { view ->
            run {
                val un = username.text.toString()
                val pw = password.text.toString()
                if (un == "test"  && pw == "1234"){
                    val i = Intent(this@LoginActivity, GuideActivity::class.java)
                    startActivity(i)

                } else {
                    Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}