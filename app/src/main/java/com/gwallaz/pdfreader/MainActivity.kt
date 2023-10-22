package com.gwallaz.pdfreader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import com.gwallaz.pdfreader.ui.theme.PDFReaderTheme
import com.gwallaz.pdfreader.user_interface.bottom_navigation.Bottombar
import com.gwallaz.pdfreader.user_interface.navigation.Screens
import com.gwallaz.pdfreader.user_interface.screens.AllFiles
import com.gwallaz.pdfreader.viewmodel.ViewModel

class MainActivity : ComponentActivity() {

    private val darkModeViewModel : ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val nightMode = if (darkModeViewModel.isDarkMode.value){
                AppCompatDelegate.MODE_NIGHT_YES
            }else{
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(nightMode)

            PDFReaderTheme(darkTheme = darkModeViewModel.isDarkMode.value) {
            AllFiles(this, darkModeViewModel)
            }
               Bottombar(this)
           }


            }
        }






