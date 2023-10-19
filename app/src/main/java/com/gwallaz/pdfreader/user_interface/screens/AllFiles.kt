package com.gwallaz.pdfreader.user_interface.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class DrawerItems(
    val selecteditem: ImageVector? = null,
    val title: String? = null,
    val unselecteditem: ImageVector? = null,
    val switch: SwitchColors? = null
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AllFiles() {

    val items = listOf(
        DrawerItems(
            selecteditem = Icons.Filled.Email,
            title = "Browse PDF",
            unselecteditem = Icons.Outlined.Email
        ),
        DrawerItems(
            selecteditem = Icons.Filled.Share,
            title = "Share App",
            unselecteditem = Icons.Outlined.Share
        ),
        DrawerItems(
            selecteditem = Icons.Filled.Send,
            title = "Security Question",
            unselecteditem = Icons.Outlined.Send
        ),
        DrawerItems(
            selecteditem = Icons.Filled.AccountCircle,
            title = "Language Options",
            unselecteditem = Icons.Outlined.AccountCircle
        ),
        DrawerItems(
            selecteditem = Icons.Filled.Star,
            title = "Dark Mode",
            unselecteditem = Icons.Outlined.Star
        ),
        DrawerItems(
            selecteditem = Icons.Filled.Call,
            title = "Feedback",
            unselecteditem = Icons.Outlined.Call
        ),
        DrawerItems(
            selecteditem = Icons.Filled.Person,
            title = "Privacy Policy",
            unselecteditem = Icons.Outlined.Person
        ),
        DrawerItems(
            title = "Settings"
        ),
        DrawerItems(
            title = "Keep screen on"
        ),
        DrawerItems(
            title = "Version: 1.3.5c"
        )
    )
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerScope  = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }


    ModalNavigationDrawer(
        drawerContent = {
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(16.dp))
                            items.forEachIndexed{index, item ->
                                NavigationDrawerItem(
                                    label = {
                                        item.title?.let { Text(text = it) }
                                    },
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        selectedItemIndex = index
                                        drawerScope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    icon = {
                                        (if (index == selectedItemIndex){
                                            item.selecteditem
                                        }else{
                                            item.unselecteditem
                                        })?.let {
                                            Icon(
                                                imageVector = it,
                                                contentDescription =item.title )
                                        }
                                    },
                                    modifier = Modifier.
                                            padding(NavigationDrawerItemDefaults.ItemPadding)

                                )

                            }
                        }
        },
        drawerState = drawerState
        )

    {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
            topBar = {
                MediumTopAppBar(
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            text = "All files",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,

                            )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            drawerScope.launch {
                                drawerState.open()
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Navigation Icon"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search action"
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check box"
                            )
                        }
                    },
                    scrollBehavior = scrollBehaviour
                )
            },
        ) {

        }
    }
}



