package com.example.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MyViewModel() : ViewModel() {
    // para que sea mas facil la etiqueta del log
    private val TAG_LOG: String = "mensaje ViewModel"

    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    val numbers = mutableListOf<Int>()

    // definimos una MutableLiveData
    // para poder observar los valores de la MutableList<Int>
    val livedata_numbers = MutableLiveData<MutableList<Int>>()

    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        livedata_numbers.value = numbers
    }

    /**
     * añadimos entero random al
     */
    fun sumarRandom() {
        // añadimos entero random a la lista
        numbers.add(Random.nextInt(0, 4))
        // actualizamos el livedata, de esta manera si hay un observador
        // este recibirá la nueva lista
        livedata_numbers.setValue(numbers)
        // la mostramos en el logcat
        Log.d(TAG_LOG, "Añadimos Array al livedata:" + numbers.toString())
    }

}