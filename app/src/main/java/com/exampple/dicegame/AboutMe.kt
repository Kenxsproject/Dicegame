package com.exampple.dicegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AboutMe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)
//switches between the about me page and home menu
        val button: Button = findViewById(R.id.button3)
        button.setOnClickListener {
            val intent = Intent(this@AboutMe, MainActivity::class.java)
            startActivity(intent)
        }

    }
}