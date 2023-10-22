package com.gwallaz.pdfreader.user_interface.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gwallaz.pdfreader.user_interface.screens.AllFiles
import com.gwallaz.pdfreader.user_interface.screens.Favourite
import com.gwallaz.pdfreader.user_interface.screens.Recent
import com.gwallaz.pdfreader.user_interface.screens.Tools
import com.gwallaz.pdfreader.viewmodel.ViewModel


@Composable
    fun BottomNav(navController: NavHostController,context: Context){
        NavHost(navController = navController, startDestination = Screens.AllFiles.route){
            composable(Screens.AllFiles.route){
                val darkViewModel: ViewModel = ViewModel()
                val s = darkViewModel.isDarkMode.value
                AllFiles(context,darkViewModel)
            }

            composable(Screens.Recent.route){

                Recent()
            }

            composable(Screens.Favourite.route){
                Favourite()
            }

            composable(Screens.Tools.route){
                Tools()
            }
            }

        }

