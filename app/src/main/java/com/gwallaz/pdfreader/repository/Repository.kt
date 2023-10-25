package com.gwallaz.pdfreader.repository

import android.content.ContentResolver
import android.media.MediaMetadataRetriever
import android.provider.MediaStore
import androidx.lifecycle.LiveData
import com.gwallaz.pdfreader.roomdatabase.FileTable
import com.gwallaz.pdfreader.roomdatabase.FilesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(
    private val fileDao: FilesDao,
    private val contentResolver: ContentResolver
) {

     val getAllFiles: LiveData<List<FileTable>> = fileDao.getAllFiles()
    suspend fun upsertFilesRecord(fileTable: FileTable){
        fileDao.upsertFilesRecord(fileTable)
    }

    private fun fetchPDFiles(): List<PDFFile>{

        val pdfs = mutableListOf<PDFFile>()
        val uri = MediaStore.Files.getContentUri("external")
        val projection = arrayOf(
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.DATE_ADDED
        )
        val selection = "${MediaStore.Files.FileColumns.MIME_TYPE} = ?"
        val selectionArgs = arrayOf("application/pdf")
        val cursor = contentResolver.query(uri, projection, selection, selectionArgs, null)

        cursor?.use { cursor ->
            val nameColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.SIZE)
            val dataColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)
            val creationDateColumn = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATE_ADDED)

            while (cursor.moveToNext()){

                val filename = cursor.getString(nameColumn)
                val filesize = cursor.getLong(sizeColumn)
                val directory = cursor.getString(dataColumn)
                val creationDate = cursor.getLong(creationDateColumn)

                val pdf = PDFFile(filename, filesize, directory, creationDate)
                pdfs.add(pdf)
            }
        }
        return pdfs
    }


    suspend fun fetchAndStoreFiles(){
        withContext(Dispatchers.IO){
            val fetchPdfiles = fetchPDFiles()

            for (pdf in fetchPdfiles){
                val fileTable = FileTable(
                    filename = pdf.filename,
                    fileType = "PDF",
                    fileSize = pdf.fileSize,
                    directory = pdf.directory,
                    creationDate = pdf.creationDate
                )
                upsertFilesRecord(fileTable)
            }
        }
    }
}