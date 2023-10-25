package com.gwallaz.pdfreader.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "files")
data class FileTable (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val filename: String,
    val fileType: String,
    val fileSize: Long,
    val directory: String,
    val creationDate: Long //Store date as timestamp
        )

