package com.gwallaz.pdfreader.user_interface.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gwallaz.pdfreader.MainActivity
import com.gwallaz.pdfreader.user_interface.navigation_drawer_actions.ShareApp
import com.gwallaz.pdfreader.viewmodel.ViewModel
import kotlinx.coroutines.launch

data class DrawerItems(
    val selecteditem: ImageVector? = null,
    val title: String? = null,
    val unselecteditem: ImageVector? = null,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AllFiles(context: Context, darkViewModel: ViewModel ) {

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
            unselecteditem = Icons.Outlined.Send,

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
    val drawerScope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var checkedSecurity by remember { mutableStateOf(false) }
    var checkedDarkmode by remember { mutableStateOf(false) }
    var checkedKeepscreenon by remember { mutableStateOf(false) }
    val share  = ShareApp()
    val textMessage = "Hey, I am using PDFReader and I am having a wonderful experience. You can download it from Play Store"

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                        .fillMaxWidth(0.7f)
                        .alpha(0.3f)
                )
                Column {
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                item.title?.let { Text(text = it) }

                                //Adding switch widget
                                when(item.title){
                                    "Security Question" -> Switch(
                                        checked = checkedSecurity,
                                        onCheckedChange ={checkedSecurity = it},
                                        modifier = Modifier
                                            .absolutePadding(left = 140.dp)
                                            .size(20.dp)
                                            .scale(0.7f, 0.7f),
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.Cyan,
                                            checkedTrackColor = Color.Transparent,
                                            checkedBorderColor = Color.Cyan

                                        )
                                    )
                                     //Setting night mode
                                    "Dark Mode" ->  Switch(
                                        checked = darkViewModel.isDarkMode.value,
                                        onCheckedChange ={checked ->
                                            darkViewModel.isDarkMode.value = checked
                                          //  val nightMode = if(checked){
                                           //     AppCompatDelegate.MODE_NIGHT_YES
                                           // } else {
                                           //     AppCompatDelegate.MODE_NIGHT_NO
                                          //  }
                                           // AppCompatDelegate.setDefaultNightMode(nightMode)
                                        },
                                        modifier = Modifier
                                            .absolutePadding(left = 140.dp)
                                            .size(20.dp)
                                            .scale(0.7f, 0.7f),
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.Cyan,
                                            checkedBorderColor = Color.Cyan,
                                            checkedTrackColor = Color.Transparent

                                        )
                                    )

                                    "Keep screen on" ->  Switch(
                                        checked = checkedKeepscreenon,
                                        onCheckedChange ={checkedKeepscreenon = it},
                                        modifier = Modifier
                                            .absolutePadding(left = 140.dp)
                                            .size(20.dp)
                                            .scale(0.7f, 0.7f),
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.Cyan,
                                            checkedTrackColor = Color.Transparent,
                                            checkedBorderColor = Color.Cyan
                                        )

                                            )

                                    "Settings" -> Text(text = "Settings",
                                    color = Color.LightGray
                                        )
                                    "Version: 1.3.5c" -> Text(text = "Version: 1.3.5c",
                                    color = Color.LightGray
                                        )
                                }
                            },

                            selected = index == selectedItemIndex,


                            onClick = {
                                selectedItemIndex = index

                                //Get exact item clicked
                                when(selectedItemIndex){
                                    0 -> {}
                                    1 -> {
                                        drawerScope.launch {
                                            drawerState.close()
                                        }
                                    }
                                    2 -> {
                                        share.share(context,textMessage)

                                        drawerScope.launch {
                                            drawerState.close()
                                        }
                                    }
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
                                .padding(0.dp)
                                .fillMaxWidth(0.8f)
                        )

                        //Adding divider to the navigation drawer
                        if (item.title in listOf("PDF Reader", "Privacy Policy")) {
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(0.7f)
                                    .padding(start = 4.dp),
                                color = Color.LightGray
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
    //Background content inside lambda of ModalNavigationDrawer
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

}

}









