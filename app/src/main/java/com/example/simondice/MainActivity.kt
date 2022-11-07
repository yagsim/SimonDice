package com.example.simondice

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() {

    private var arraySentencia = ArrayList<Int>()
    private var arrayColor = ArrayList<Int>()   //guardaremos el número de color de cada ronda
    private var inicio = false

    private var encenderColores: Job? = null
    lateinit var botonRojo: Button
    lateinit var botonAzul: Button
    lateinit var botonVerde: Button
    lateinit var botonAmarillo: Button
    lateinit var botonInicio: Button
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        botonRojo = findViewById(R.id.botonRojo)
        botonAzul = findViewById(R.id.botonAzul)
        botonVerde = findViewById(R.id.botonVerde)
        botonAmarillo = findViewById(R.id.botonAmarillo)
        botonInicio = findViewById(R.id.botonInicio)
        textView = findViewById(R.id.textView)
        botonInicio.setOnClickListener {
        val courrutine=GlobalScope.launch(Dispatchers.Main){
            inicio = true;
            botonInicio.visibility = View.INVISIBLE
            cambiarColor(true)
            Log.d("Inicio", "click en inicio")
        }
        courrutine.start()


            botonRojo.setOnClickListener() {
                Log.d("botónRojopresionado:", "1")
                comprobarColor(botonRojo,1,R.color.red,R.color.rojoApagado)
            }
            botonAzul.setOnClickListener() {
                Log.d("botónAzulpresionado:", "2")
                comprobarColor(botonAzul,2,R.color.blue,R.color.azulApagado)
            }
            botonVerde.setOnClickListener() {
                Log.d("botónVerdepresionado:", "3")
                comprobarColor(botonVerde,3,R.color.green,R.color.verdeApagado)

            }
            botonAmarillo.setOnClickListener() {
                Log.d("botónAmarillopresionado:", "4")
                comprobarColor(botonAmarillo,4,R.color.yellow,R.color.amarilloApagado)

            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun cambiarColor(nuevo: Boolean) {
        arraySentencia= ArrayList()
        if (nuevo) {
            colorenEnSecuencia()
        }
        encenderColores = GlobalScope.launch(Dispatchers.Main) {     //courutine para encender los colores
                inicio = false

                for (i in 0 until arrayColor.size) {//con esto recorremos las rondas
                    delay(350L)
                    when (arrayColor[i]) {      //de la ronda que sea cogemos el numero del color y según cual sea lo muestra
                        1 -> {
                            botonRojo.setBackgroundResource(R.color.red)
                            delay(850)
                            botonRojo.setBackgroundResource(R.color.rojoApagado)
                            delay(70)
                        }
                        2 -> {
                            botonAzul.setBackgroundResource(R.color.blue)
                            delay(850)
                            botonAzul.setBackgroundResource(R.color.azulApagado)
                            delay(70)
                        }
                        3 -> {
                            botonVerde.setBackgroundResource(R.color.green)
                            delay(850)
                            botonVerde.setBackgroundResource(R.color.verdeApagado)
                            delay(70)
                        }
                        else -> {
                            botonAmarillo.setBackgroundResource(R.color.yellow)
                            delay(850)
                            botonAmarillo.setBackgroundResource(R.color.amarilloApagado)
                            delay(70)
                        }
                    }

                }
                inicio=true
            }
    }

    //métodos generarSecuencia donde se genera una secuencia aleatoria y nuevoColor donde se agreaga un numero de color a cada uno de los colores
    private fun colorenEnSecuencia() {
        var numColor = 0
        when (generarSecuencia()) {
            R.color.red -> numColor = 1
            R.color.blue -> numColor = 2
            R.color.green -> numColor = 3
            R.color.yellow -> numColor = 4
        }
        arrayColor.add(numColor)   //añado al array el color
    }

    fun generarSecuencia(): Int {
        val color = when (Random().nextInt(4) + 1) {
            1 -> R.color.red
            2 -> R.color.blue
            3 -> R.color.green
            else -> R.color.yellow
        }
        return color
    }

    private fun comprobarColor(boton:Button,numColor:Int,colorEnc : Int, colorApg : Int) {
        if (inicio) {
                if (arraySentencia.size == arrayColor.size-1){
                    arraySentencia.add(numColor)   //añado el numero del color al array sentencia
                if (arraySentencia == arrayColor) {
                    val enc = GlobalScope.launch(Dispatchers.Main) {
                        boton.setBackgroundResource(colorEnc)
                        delay(300L)
                        boton.setBackgroundResource(colorApg)
                    }
                    enc.start()
                    acierto()
                    textView.setText("ADIVINASTE")
                }else {
                    error()
                    textView.setText("PERDISTE")
                }
                } else if (arraySentencia.size < arrayColor.size) {  //si el tamaño del array de sentencia es menor al los colores se ira comprobando color a color mientras existan
                arraySentencia.add(numColor)
                    if (arraySentencia[arraySentencia.size-1] != arrayColor[arraySentencia.size-1]) {
                        error()//si la sentencia es distinta al color fallo
                        textView.setText("PERDISTE")
                    }else {    //sino es distinta pues se encendera y apagara
                        val enc = GlobalScope.launch(Dispatchers.Main) {
                            boton.setBackgroundResource(colorEnc)
                            delay(300L)
                            boton.setBackgroundResource(colorApg)
                        }
                        enc.start()
                    }
                }
        }
    }

    private fun acierto(){
        val acierto = GlobalScope.launch(Dispatchers.Main) {
            inicio = false
            delay(600L)
            cambiarColor(true)
        }
        acierto.start()

        inicio = true
    }

    private fun error(){
        inicio = false

        val error = GlobalScope.launch(Dispatchers.Main) {
            botonRojo.setBackgroundResource(R.color.red)
            botonAzul.setBackgroundResource(R.color.red)
            botonVerde.setBackgroundResource(R.color.red)
            botonAmarillo.setBackgroundResource(R.color.red)
            delay(100L)
            botonRojo.setBackgroundResource(R.color.red)
            botonAzul.setBackgroundResource(R.color.red)
            botonVerde.setBackgroundResource(R.color.red)
            botonAmarillo.setBackgroundResource(R.color.red)
        }
        error.start()
        botonInicio.visibility=View.VISIBLE
    }
}









