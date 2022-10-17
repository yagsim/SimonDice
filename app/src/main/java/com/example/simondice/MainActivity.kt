package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonInicio: Button = findViewById(R.id.botonInicio)
        botonInicio.setOnClickListener(){

        }
    }
    private fun mostrarColor(){
        
    }

}