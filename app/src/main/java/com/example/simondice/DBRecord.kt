package com.example.simondice


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(           //clase donde se crea la DB
    entities = [Record::class],
    version = 1
)
abstract class RecordDB : RoomDatabase() {
    abstract fun recordSel(): RecordSel   //esta clase recoge el metodo RecordSel de Record
}