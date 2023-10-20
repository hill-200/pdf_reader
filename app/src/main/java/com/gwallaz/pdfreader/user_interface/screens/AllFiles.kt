package com.gwallaz.pdfreader.user_interface.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
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
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
            title = "PDF Reader"
        ),
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
    val backgroundAlpha = if (drawerState.isOpen) 0.3f else 1.0f
    val backgroundColor = androidx.compose.ui.graphics.Color.Gray
    val drawerScope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val drawerWidth =
        (LocalDensity.current.run { LocalConfiguration.current.screenWidthDp.dp * 3 / 4 })
    val maxHeight = 700.dp


    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .fillMaxWidth(0.7f)
                        .alpha(0.3f)
                )

                Column {


                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(

                            label = {
                                item.title?.let { Text(text = it) }
                                //when(item.title){
                                //     "Settings" -> Divider(modifier = Modifier.height(2.dp).padding(16.dp), color = androidx.compose.ui.graphics.Color.LightGray)
                                // }


                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                drawerScope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                (if (index == selectedItemIndex) {
                                    item.selecteditem
                                } else {
                                    item.unselecteditem
                                })?.let {
                                    Icon(
                                        imageVector = it,
                                        contentDescription = item.title
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)

                                .fillMaxWidth(0.7f)


                        )
                        if (item.title in listOf("PDF Reader", "Privacy Policy")) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(start = 4.dp),
                                color = androidx.compose.ui.graphics.Color.LightGray
                            )
                        }


                    }
                }
            }
        },
        drawerState = drawerState,
        modifier = Modifier
            .verticalScroll(rememberScrollState(), true)
            .height(700.dp)


    )
    {
        Box {


        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehaviour.nestedScrollConnection)
                ,

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
}}









