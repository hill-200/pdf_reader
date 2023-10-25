package com.gwallaz.pdfreader.repository

data class PDFFile(
    val filename: String,
    val fileSize: Long,
    val directory: String,
    //val imageMetaData:  ImageMetaData?,
    val creationDate: Long
)
