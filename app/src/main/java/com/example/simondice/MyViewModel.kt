package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    // para que sea mas facil la etiqueta del logt
    private val TAG_LOG: String = "mensaje ViewModel"
    private val tag = "RealTime"
    private lateinit var database_record: DatabaseReference

    // definimos una MutableLiveData
    // para poder observar los valores de la MutableList<Int>(numbers)
    val ronda= MutableLiveData<Int>()
    var record= MutableLiveData<Int>()
   // var room: RecordDB?=null


    // inicializamos variables cuando instanciamos y creamos room
    init {

        ronda.value = 0

        /*room= Room
            .databaseBuilder(context,
                RecordDB::class.java, "record")    //la creamosDB con el nombre record
            .build()
         val Courrutine = GlobalScope.launch(Dispatchers.Main) {    //ataque a DB al iniciar el modelo

                 try {         //el try para que no se cree solo una vez la DB(si no esta creada que la cree)
                     record.value = room!!.recordSel().getAll()[0].puntuacion     //pone el método si no hay un valor
                 } catch(ex : IndexOutOfBoundsException) {
                     room!!.recordSel().insert(listOf(Record(1, 0)))         //actualiza el record si el record es mayor con id 1
                     record.value= room!!.recordSel().getAll()[0].puntuacion
                 }
         }
        Courrutine.start()
        Log.d(TAG_LOG, "Db:" + room)*/

        //acceso a la BD Firebase
        database_record = Firebase.database("https://simondice-96c2d-default-rtdb.firebaseio.com/") //tenemos que recoger url de google
            .getReference("record")     //recojo de la ruta la columna record
        //El listener sera
        val recordListener = object : ValueEventListener {         //clase abstracta
            override fun onDataChange(dataSnapshot: DataSnapshot) {   //si hay cambios en el DB lo recogemos
                record.value = dataSnapshot.getValue<Int>()
            }

            override fun onCancelled(error: DatabaseError) {       //se implementa de Value Event Listener
                Log.d(tag, "recordListener:OnCancelled", error.toException())   //donde recogemos el error
            }
        }
        //se añade el listener a la BD
        database_record.addValueEventListener(recordListener)

    }

        fun actualizarRecord() {
            record.value = ronda.value
            database_record.setValue(record.value)      //metera en la base de datos el valor de record
            /* val newRecord = GlobalScope.launch(Dispatchers.Main) {

            room!!.recordSel().update(Record(1, ronda.value!!))
        }
        newRecord.start()*/
        }

        fun sumaRonda() {
            ronda.value = ronda.value?.plus(1)      //añade ronda
        }

}