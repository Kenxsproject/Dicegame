package com.exampple.dicegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //This function takes you to the About me page
        val button2: Button = findViewById(R.id.button)
        button2.setOnClickListener{
            val intent = Intent(this@MainActivity, AboutMe::class.java)
            startActivity(intent)
        }

        //This Function takes you to the Gamescreen page
        val button:Button = findViewById(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, Gamescreen::class.java)
            startActivity(intent)
        }




    }
}