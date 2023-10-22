package com.gwallaz.pdfreader.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {
    val isDarkMode = mutableStateOf(false)
}