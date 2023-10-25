package com.gwallaz.pdfreader.viewmodel

import android.app.Application
import android.content.ContentResolver
import android.provider.MediaStore
import android.provider.MediaStore.Files
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gwallaz.pdfreader.repository.Repository
import com.gwallaz.pdfreader.roomdatabase.FileDatabase
import com.gwallaz.pdfreader.roomdatabase.FileTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application, private val contentResolver: ContentResolver): AndroidViewModel(application) {
    val isDarkMode = mutableStateOf(false)

    val getAllFiles: LiveData<List<FileTable>>
    private val repository: Repository


     init {
         val filesDao = FileDatabase.getDatabase(application).getFilesDao()
         repository = Repository(filesDao,contentResolver)
         getAllFiles = repository.getAllFiles
     }

    fun upsertFilesRecord(fileTable: FileTable){
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchAndStoreFiles()
        }
    }


}