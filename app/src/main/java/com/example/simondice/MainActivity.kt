package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var inicio= false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonInicio: Button = findViewById(R.id.botonInicio)
        botonInicio.setOnClickListener() {
        botonInicio.visibility= View.INVISIBLE
            inicio=true
        }

    }

    override fun onResume() {
        super.onResume()
        val botonRojo: Button = findViewById(R.id.botonRojo)
        val botonAzul: Button = findViewById(R.id.botonAzul)
        val botonVerde: Button = findViewById(R.id.botonVerde)
        val botonAmarillo: Button = findViewById(R.id.botonAmarillo)
        if(inicio){
            mostrarColor()
        }
    }

    private fun mostrarColor(): Int {
        val color = when (Random.nextInt(4) + 1) {
            1 -> R.color.red
            2 -> R.color.blue
            3 -> R.color.green
            else -> R.color.yellow
        }
        return color
    }
}