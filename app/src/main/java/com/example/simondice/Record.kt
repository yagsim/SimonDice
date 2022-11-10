package com.example.simondice

import androidx.room.*

@Entity
data class Record (         //tendra que ser una class de tipo data
    @PrimaryKey(autoGenerate = true)       //autogeneramos la clave primaria
    val id : Int,         //parametros el id y la puntuacion(id se sumara uno cada vez que se juegue)
    val puntuacion : Int
)

@Dao
interface RecordSel{
    @Query("SELECT * from Record")               //recogeremos
    suspend fun getAll(): List<Record>

    @Insert(onConflict = OnConflictStrategy.IGNORE)    //añadiremos
    suspend fun insert(record: List<Record>)

    @Update                  //actulizaremos si se bate el record
    suspend fun update(record: Record)
}