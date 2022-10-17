package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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
            arrayColor.add(cambiarColor(botonRojo,botonAzul,botonVerde,botonAmarillo))
        }
        botonRojo.setOnClickListener{
            if(inicio){
                if(arrayClick.size==arrayColor.size-1){
                    arrayClick.add(1)
               if(arrayClick==arrayColor){
                        textView.text="Correcto"
               }else{
                        textView.text="ERROR.PERDISTE"
                    }
                }else if(arrayClick.size<arrayColor.size){
                    arrayClick.add(1)
                }
            }
        }
        botonAzul.setOnClickListener{
            if(inicio){
                if(arrayClick.size==arrayColor.size-1){
                    arrayClick.add(2)
                    if(arrayClick==arrayColor){
                        textView.text="Correcto"
                    }else{
                        textView.text="ERROR.PERDISTE"
                    }
                }else if(arrayClick.size<arrayColor.size){
                    arrayClick.add(2)
                }
            }
        }
        botonVerde.setOnClickListener{
            if(inicio){
                if(arrayClick.size==arrayColor.size-1){
                    arrayClick.add(3)
                    if(arrayClick==arrayColor){
                        textView.text="Correcto"
                    }else{
                        textView.text="ERROR.PERDISTE"
                    }
                }else if(arrayClick.size<arrayColor.size){
                    arrayClick.add(3)
                }
            }
        }
        botonAmarillo.setOnClickListener{
            if(inicio){
                if(arrayClick.size==arrayColor.size-1){
                    arrayClick.add(4)
                    if(arrayClick==arrayColor){
                        textView.text="Correcto"
                    }else{
                        textView.text="ERROR.PERDISTE"
                    }
                }else if(arrayClick.size<arrayColor.size){
                    arrayClick.add(4)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val botonRojo: Button = findViewById(R.id.botonRojo)
        val botonAzul: Button = findViewById(R.id.botonAzul)
        val botonVerde: Button = findViewById(R.id.botonVerde)
        val botonAmarillo: Button = findViewById(R.id.botonAmarillo)
        if(inicio){
            cambiarColor(botonRojo ,botonAzul ,botonVerde, botonAmarillo)
        }
    }
    private fun cambiarColor(botonRojo:Button ,botonVerde: Button , botonAmarillo:Button, botonAzul:Button): Int {

        var color=0
        when(mostrarColor()){
            R.color.red-> color=1
            R.color.blue-> color=2
            R.color.green->color=3
            R.color.yellow->color=4
        }

        return color
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