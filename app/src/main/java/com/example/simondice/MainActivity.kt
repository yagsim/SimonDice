package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var inicio= false
    private var arrayColor=ArrayList<Int>()
    private var arrayClick=ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botonRojo: Button = findViewById(R.id.botonRojo)
        val botonAzul: Button = findViewById(R.id.botonAzul)
        val botonVerde: Button = findViewById(R.id.botonVerde)
        val botonAmarillo: Button = findViewById(R.id.botonAmarillo)
        val botonInicio: Button = findViewById(R.id.botonInicio)
        val textView:TextView =findViewById(R.id.textView)
        botonInicio.setOnClickListener() {
        botonInicio.visibility= View.INVISIBLE
            inicio=true
            mostrarColor()
            aleatorio(botonRojo,botonAzul,botonVerde,botonAmarillo)
            cambiarColor(botonRojo,botonAzul,botonVerde,botonAmarillo)

        }




    }


    override fun onResume() {
        super.onResume()
        val botonRojo: Button = findViewById(R.id.botonRojo)
        val botonAzul: Button = findViewById(R.id.botonAzul)
        val botonVerde: Button = findViewById(R.id.botonVerde)
        val botonAmarillo: Button = findViewById(R.id.botonAmarillo)

    }

    private fun aleatorio(botonRojo:Button ,botonVerde: Button , botonAmarillo:Button, botonAzul:Button) {

        val randomInt: Int = Random.nextInt(4) + 1
         val courutine=GlobalScope.launch(Dispatchers.Main) {


             val cambioColor = when (randomInt) {
                 1 -> botonRojo.setBackgroundResource(mostrarColor())
                 2 -> botonAzul.setBackgroundResource(mostrarColor())
                 3 -> botonVerde.setBackgroundResource(mostrarColor())
                 else -> botonAmarillo.setBackgroundResource(mostrarColor())
             }

         }

    }


    private fun mostrarColorApagado(): Int {
        val color = when (Random.nextInt(4) + 1) {
            1 -> R.color.red
            2 -> R.color.blue
            3 -> R.color.green
            else -> R.color.yellow
        }
        return color
    }
    private fun mostrarColor():Int{
        var color=0
        when(mostrarColor()){
            R.color.red-> color=1
            R.color.blue -> color=2
            R.color.green->color=3
            R.color.yellow->color=4
        }

        return color
    }
}