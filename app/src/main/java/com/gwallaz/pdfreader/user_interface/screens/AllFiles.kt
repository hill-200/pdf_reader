package com.gwallaz.pdfreader.user_interface.screens


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gwallaz.pdfreader.R
import com.gwallaz.pdfreader.user_interface.navigation_drawer_actions.ShareApp
import kotlinx.coroutines.launch

data class DrawerItems(
    val selecteditem: ImageVector? = null,
    val bottomSheetItem: Int? = null,
    val title: String? = null,
    val unselecteditem: ImageVector? = null,

)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AllFiles(context: Context) {

    val bottomSheetContent = listOf(
        DrawerItems(
            bottomSheetItem = R.drawable.baseline_date_range_24,
            title = "Last Modified"
        ),
        DrawerItems(
            bottomSheetItem = R.drawable.baseline_content_name,
            title = "Name"
        ),
        DrawerItems(
            bottomSheetItem = R.drawable.baseline_file_size,
            title = "File Size"
        ),
        DrawerItems(
            bottomSheetItem = R.drawable.baseline_new_to_old,
            title = "From new to old"
        ),
        DrawerItems(
            bottomSheetItem = R.drawable.baseline_old_to_new,
            title = "From old to new"
        )
    )

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
    val checkedDarkmode by remember { mutableStateOf(false) }
    var checkedKeepscreenon by remember { mutableStateOf(false) }
    val share = ShareApp()
    val textMessage =
        "Hey, I am using PDFReader and I am having a wonderful experience. You can download it from Play Store"
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var bottomSheet by remember { mutableStateOf(false) }


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
                                when (item.title) {
                                    "Security Question" -> Switch(
                                        checked = checkedSecurity,
                                        onCheckedChange = { checkedSecurity = it },
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
                                    "Dark Mode" -> Switch(
                                        checked = checkedDarkmode,//darkViewModel.isDarkMode.value,
                                        onCheckedChange = {//checked ->
                                            // darkViewModel.isDarkMode.value = checked
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

                                    "Keep screen on" -> Switch(
                                        checked = checkedKeepscreenon,
                                        onCheckedChange = { checkedKeepscreenon = it },
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

                                    "Settings" -> Text(
                                        text = "Settings",
                                        color = Color.LightGray
                                    )

                                    "Version: 1.3.5c" -> Text(
                                        text = "Version: 1.3.5c",
                                        color = Color.LightGray
                                    )
                                }
                            },

                            selected = index == selectedItemIndex,


                            onClick = {
                                selectedItemIndex = index

                                //Get exact item clicked
                                when (selectedItemIndex) {
                                    0 -> {}
                                    1 -> {
                                        drawerScope.launch {
                                            drawerState.close()
                                        }
                                    }

                                    2 -> {
                                        share.share(context, textMessage)

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


        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehaviour.nestedScrollConnection),

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

                        //Action button to open bottom sheet
                        IconButton(onClick = {
                            Log.d("Message", "Action button to open bottom sheet is clicked")
                            bottomSheet = true
                            scope.launch {
                                sheetState.show()
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Filled.List,
                                contentDescription = "Bottom sheet"
                            )
                        }


                        //Action button to search files
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search action"
                            )
                        }


                        //Action button to select all files
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Check box"
                            )
                        }


                    },
                    scrollBehavior = scrollBehaviour
                )
            }
        ) { values ->
            //Content of scaffold goes in here
            if (bottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        bottomSheet = false
                    },
                    sheetState = sheetState
                ) {//Content of bottom sheet starts here
                    Text(
                        text = "Sort by:",
                        modifier = Modifier
                            .padding(start = 16.dp),
                        fontStyle = FontStyle.Normal,
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp),
                        color = Color.LightGray
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_date_range_24),
                                contentDescription = null
                            )
                            Text(
                                text = "Last Modified",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 130.dp),
                                checked = true,
                                onCheckedChange = {/* Handle checkbox state changes*/ }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_content_name),
                                contentDescription = null
                            )
                            Text(
                                text = "Name",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 185.dp),
                                checked = true,
                                onCheckedChange = {/* Handle checkbox state changes*/ }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_file_size),
                                contentDescription = null
                            )
                            Text(
                                text = "File Size",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 170.dp),
                                checked = true,
                                onCheckedChange = {/* Handle checkbox state changes*/ }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_new_to_old),
                                contentDescription = null
                            )
                            Text(
                                text = "From new to old",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 120.dp),
                                checked = true,
                                onCheckedChange = {/* Handle checkbox state changes*/ }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_old_to_new),
                                contentDescription = null
                            )
                            Text(
                                text = "From old to new",
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                            Checkbox(
                                modifier = Modifier
                                    .padding(start = 120.dp),
                                checked = true,
                                onCheckedChange = {/* Handle checkbox state changes*/ }
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //Buttons for cancelling and Ok go here
                        }


                    }

                }


            }//Content of bottom sheet ends here

            //Lazy column starts here
            LazyColumn(
                modifier = Modifier
                    .padding(values)
                    .fillMaxSize()
                // .absolutePadding(bottom = 10.dp)

            ) {
                items(100) {
                    val lazyColumnItems = LazyColumnItems()
                    lazyColumnItems.columnItems()
                }


                //items(10){
                //   Text(
                //       text = "Items $it"
                //   , modifier = Modifier.padding(16.dp)
                //      )
                //    }

            }//Lazy column ends here

        }
    }

}

