package com.gwallaz.pdfreader.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [FileTable::class], version = 1, exportSchema = true)
 abstract class FileDatabase: RoomDatabase() {

     abstract fun getFilesDao(): FilesDao

     companion object{
         private fun buildDatabase(context: Context): FileDatabase {
             return Room.databaseBuilder(
                 context, FileDatabase::class.java, "builder.db"
             ).build()
         }

         @Volatile
         private var INSTANCE: FileDatabase? = null

         @OptIn(InternalCoroutinesApi::class)
         fun getDatabase(context: Context): FileDatabase {
             if (INSTANCE == null) {
                 kotlinx.coroutines.internal.synchronized(this) {
                     INSTANCE = buildDatabase(context)
                 }
             }
             return INSTANCE!!

         }

     }
}