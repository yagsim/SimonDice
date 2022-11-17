package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    // para que sea mas facil la etiqueta del logt
    private val TAG_LOG: String = "mensaje ViewModel"


    // definimos una MutableLiveData
    // para poder observar los valores de la MutableList<Int>(numbers)
    val ronda= MutableLiveData<Int>()
    val record= MutableLiveData<Int>()
    var room: RecordDB?=null

    // inicializamos variables cuando instanciamos y creamos room
    init {

        ronda.value=0

         room= Room
            .databaseBuilder(context,
                RecordDB::class.java, "record")    //la creamosDB con el nombre record
            .build()
         val Courrutine = GlobalScope.launch(Dispatchers.Main) {    //ataque a DB al iniciar el modelo

                 try {
                     record.value = room!!.recordSel().getAll()[0].puntuacion     //pone el método si no hay un valor
                 } catch(ex : IndexOutOfBoundsException) {
                     room!!.recordSel().insert(listOf(Record(1, 0)))         //actualiza el record si el record es mayor con id 1
                     record.value= room!!.recordSel().getAll()[0].puntuacion
                 }
         }
        Courrutine.start()
        Log.d(TAG_LOG, "Db:" + room)

    }



    fun sumaRonda(){
        ronda.value=ronda.value?.plus(1)      //añade ronda
    }
    fun reseteo(){
        ronda.value=0          //reinicia las rondas al fallar
    }
}