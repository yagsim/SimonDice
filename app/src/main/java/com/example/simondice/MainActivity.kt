package com.example.simondice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {
    private var inicio= false
    private var encender : Job? = null
    private var arrayColor=ArrayList<Int>()
    private var arrayClick=ArrayList<Int>()
    private var transiccion=200L


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

            cambiarColor(botonRojo,botonAzul,botonVerde,botonAmarillo,true)

        }




    }


    override fun onResume() {
        super.onResume()
        val botonRojo: Button = findViewById(R.id.botonRojo)
        val botonAzul: Button = findViewById(R.id.botonAzul)
        val botonVerde: Button = findViewById(R.id.botonVerde)
        val botonAmarillo: Button = findViewById(R.id.botonAmarillo)

    }

    private fun aleatorio():Int{        //método
             val color = when (Random().nextInt(4)+1){
                 1 -> R.color.red
                 2 -> R.color.blue
                 3 -> R.color.green
                 else -> R.color.yellow
             }
             return color
         }



    private fun cambiarColor(botonRojo : Button, botonVerde : Button, botonAmarillo: Button, botonAzul: Button, nuevo : Boolean) {
        arrayClick= ArrayList()    //guardamosel boton que clicamos
        if(nuevo){   //si es nueva la secuencia
            mostrarColor()    //se mostraran encendidos
        }
        encender=GlobalScope.launch(Dispatchers.Main) {     //courutine para encender los colores
            inicio=false  //para que esten apagados
            for (i in 0 until arrayColor.size) {     //recorremos el array
                delay(transiccion)    //retardo para que se muestre transiccion
                when (arrayColor[i]) {      //recorremos mientras haya mostramos
                    1->
                    {
                        botonRojo.setBackgroundResource(R.color.red)
                        delay(transiccion)
                        botonRojo.setBackgroundResource(R.color.rojoApagado)
                    }
                    2->{
                    botonAzul.setBackgroundResource(R.color.blue)
                    delay(transiccion)
                    botonAzul.setBackgroundResource(R.color.azulApagado)
                    }
                    3->{
                        botonVerde.setBackgroundResource(R.color.green)
                        delay(transiccion)
                        botonVerde.setBackgroundResource(R.color.verdeApagado)

                    }
                    else->{
                        botonAmarillo.setBackgroundResource(R.color.yellow)
                        delay(transiccion)
                        botonAmarillo.setBackgroundResource(R.color.amarilloApagado)
                    }
                }
            }
            inicio=true
        }

    }
    private fun comprobarColor(botonRojo:Button ,botonVerde: Button , botonAmarillo:Button, botonAzul:Button) {

        val textView:TextView =findViewById(R.id.textView)

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

    private fun mostrarColorApagado(): Int {
        val color = when (Random1.nextInt(4) + 1) {
            1 -> R.color.red
            2 -> R.color.blue
            3 -> R.color.green
            else -> R.color.yellow
        }
        return color
    }
    private fun mostrarColor() {
        var color=0
        when(aleatorio()){
            R.color.red-> color=1
            R.color.blue -> color=2
            R.color.green->color=3
            R.color.yellow->color=4
        }
        arrayColor.add(color)
    }

}
