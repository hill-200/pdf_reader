package com.gwallaz.pdfreader.user_interface.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gwallaz.pdfreader.user_interface.screens.AllFiles
import com.gwallaz.pdfreader.user_interface.screens.Favourite
import com.gwallaz.pdfreader.user_interface.screens.Recent
import com.gwallaz.pdfreader.user_interface.screens.Tools


    @Composable
    fun BottomNav(navController: NavHostController){
        NavHost(navController = navController, startDestination = Screens.AllFiles.route){
            composable(Screens.AllFiles.route){
                AllFiles()
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

