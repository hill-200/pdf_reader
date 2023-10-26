package com.gwallaz.pdfreader

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.gwallaz.pdfreader.ui.theme.PDFReaderTheme
import com.gwallaz.pdfreader.user_interface.bottom_navigation.Bottombar
import com.gwallaz.pdfreader.user_interface.screens.AllFiles
import com.gwallaz.pdfreader.viewmodel.ViewModel

class MainActivity : ComponentActivity() {

    //private val darkModeViewModel: ViewModel by viewModels()
    private val REQUESTPERMISSIONCODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            //val nightMode = if (darkModeViewModel.isDarkMode.value) {
              //  AppCompatDelegate.MODE_NIGHT_YES
         //   } else {
             //   AppCompatDelegate.MODE_NIGHT_NO
        //    }
          //  AppCompatDelegate.setDefaultNightMode(nightMode)

            PDFReaderTheme {
                AllFiles(this)
            }
            Bottombar(this)
        }

        requestWritePermission()

    }

    private fun requestWritePermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUESTPERMISSIONCODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUESTPERMISSIONCODE) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(
                    this@MainActivity,
                    "Permission required for accessing the files",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}






