package com.gwallaz.pdfreader.user_interface.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gwallaz.pdfreader.R

class LazyColumnItems {


    @Composable
    fun columnItems() {
        Box {


            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable {

                        Log.d("Row clicked", "All the items have been clicked")
                    }
            ) {
                Card(
                    modifier = Modifier
                        .absolutePadding(right = 8.dp, left = 8.dp, bottom = 3.dp)
                        .size(60.dp, 70.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                }
                Box {
                Column(
                    modifier = Modifier
                        .absolutePadding(top = 2.dp)
                        .size(200.dp,70.dp)
                ) {
                    Text(
                        text = "My name is hillary imwene ikwara hillary ikwara",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                    Row {
                        OutlinedCard(
                            border = BorderStroke(1.dp, Color.Gray),
                            modifier = Modifier
                                .size(40.dp, 20.dp)

                        ) {

                            Text(
                                text = "PDF",
                                color = Color.Gray,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .absolutePadding(left = 3.dp),
                                textAlign = TextAlign.Center


                            )

                        }

                        Text(
                            text = "10/16/2023",
                            color = Color.Gray,
                            modifier = Modifier
                                .absolutePadding(left = 4.dp)


                        )

                        Text(
                            text = "83 kB",
                            color = Color.Gray,
                            modifier = Modifier
                                .absolutePadding(left = 4.dp)
                        )
                    }




                    Row(
                        modifier = Modifier
                            .absolutePadding(top = 6.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.folder),
                            contentDescription = null,
                            modifier = Modifier
                                .absolutePadding(right = 4.dp)
                        )
                        Text(
                            text = "Telegram",
                            color = Color.Gray
                        )

                    }
                }
            }


                Image(

                    painter = painterResource(id = R.drawable.more_vert),
                    contentDescription = null,
                    modifier = Modifier
                        .absolutePadding(top = 30.dp)
                        .clickable {
                            Log.d("LazyColumn", "Menu item clicked")
                        }

                )
            }
        }
    }
}



