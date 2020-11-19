package com.alex.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnOpen:TextView = findViewById(R.id.btn_open)
        btnOpen.setOnClickListener{OpenMainActivity()}
    }

    fun OpenMainActivity(){
        val intent = Intent(this@MainActivity,CalculatorActivity::class.java)
        startActivity(intent)
    }
}