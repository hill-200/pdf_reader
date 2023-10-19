package com.gwallaz.pdfreader.user_interface.bottom_navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gwallaz.pdfreader.user_interface.navigation.BottomNav
import com.gwallaz.pdfreader.user_interface.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
    fun Bottombar() {
        val navController:NavHostController = rememberNavController()
        val buttonsVisible = remember{mutableStateOf(true)}

        Scaffold(
            bottomBar = {
                BottomBar(navController = navController, state = buttonsVisible)
            }
        ) {
          BottomNav(navController = navController)

        }
    }



@Composable
fun BottomBar(navController: NavHostController,state: MutableState<Boolean>){
    val screens = listOf(
        Screens.AllFiles,
        Screens.Recent,
        Screens.Favourite,
        Screens.Tools
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },

                icon = {
                    Icon(imageVector = screen.image!!,
                        contentDescription = "Navigation Icon")
                },

                selected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true,

                onClick =
                {
                    navController.navigate(screen.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue
                )
            )
        }

    }

 //   @Composable
   // fun RowScope.AddItem(
    //    screen: Screens,
    //    currentDestination: NavDestination?,
   //     navController: NavHostController
 //   ){

       // NavigationBarItem(
         //   label = {
            //        Text(text = screen.title)
        //            },

          //  icon = {
               // Icon(imageVector = screen.image!!,
       //             contentDescription = "Navigation Icon")
           // },
         //   selected = currentDestination?.hierarchy?.any {
                                       //                   it.route == screen.route
           // } == true,
           // onClick = {
          //      navController.navigate(screen.route)
     //   }

 //       )
   // }

}