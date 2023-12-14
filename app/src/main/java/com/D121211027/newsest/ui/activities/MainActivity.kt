package com.D121211027.newsest.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.D121211027.newsest.R
import com.D121211027.newsest.ui.activities.PortalActivity
import com.D121211027.newsest.ui.theme.NewsestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Header()
                }
            }
        }
    }

    @Composable
    fun Header() {
        Box(modifier = Modifier
            .shadow(
                elevation = 1.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .width(432.dp)
            .height(450.dp)
            .background(color = Color(0xFFFFFFFF)),
            contentAlignment = Alignment.Center,
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Image(
                    modifier = Modifier
                        .width(150.dp),
                    painter = painterResource(id = R.drawable.cnn),
                    contentDescription = null,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 15.dp)
                        .fillMaxWidth(),
                ) {
                    Box(modifier = Modifier
                        .width(320.dp)
                        .height(40.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xFFC60000),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 8.dp)
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                        ) {
                            Text(modifier = Modifier,
                                text = "Cari berita...",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFA8A8A8),
                                )
                            )
                            Image(
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp),
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = null
                            )
                        }
                    }
                    Image(
                        modifier = Modifier
                            .width(45.dp)
                            .height(45.dp),
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp, bottom = 50.dp),
                    text = "Ingin tahu berita terbaru hari ini?",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFC60000),
                    )
                )
                Column(verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp)
                ) {
                    val context = LocalContext.current
                    Box(modifier = Modifier.fillMaxWidth()
                        .clickable {
                            val intent = Intent(context, PortalActivity::class.java)
                            context.startActivity(intent)
                        }
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .width(180.dp)
                        .height(50.dp)
                        .background(
                            color = Color(0xFFC60000),
                            shape = RoundedCornerShape(size = 8.dp)
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "LIHAT SEMUA BERITA",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                }
            }
        }
    }
}


