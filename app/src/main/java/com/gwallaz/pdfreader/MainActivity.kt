package com.gwallaz.pdfreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gwallaz.pdfreader.user_interface.bottom_navigation.Bottombar
import com.gwallaz.pdfreader.user_interface.screens.AllFiles

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AllFiles()
            Bottombar()

            }
        }
    }



