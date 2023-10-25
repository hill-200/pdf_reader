package com.gwallaz.pdfreader.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface FilesDao {

    @Upsert
    suspend fun upsertFilesRecord(fileTable: FileTable)

   @Query("SELECT * FROM files ORDER BY id ASC")
   fun getAllFiles() : LiveData<List<FileTable>>


}