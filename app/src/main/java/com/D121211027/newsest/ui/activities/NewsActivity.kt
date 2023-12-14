package com.D121211027.newsest.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211027.newsest.R
import com.D121211027.newsest.data.models.Article
import com.D121211027.newsest.ui.activities.PortalActivity
import com.D121211027.newsest.ui.theme.NewsestTheme
import java.text.SimpleDateFormat
import java.util.Locale

class NewsActivity : ComponentActivity() {
    private var selectedArticle: Article? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedArticle = intent.getParcelableExtra("ARTICLE")
        setContent {
            NewsestTheme {
                Surface(
                    color = Color.White
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        BarNews()
                        Detail()
                    }
                }
            }
        }
    }

    @Composable
    fun BarNews() {
        val context = LocalContext.current
        Box(modifier = Modifier
            .shadow(elevation = 5.dp, spotColor = Color(0xFF000000))
            .fillMaxWidth()
            .height(70.dp)
            .background(color = Color(0xFFFFFFFF))
        ) {
            Row(modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .width(20.dp)
                        .clickable {
                            val intent = Intent(context, PortalActivity::class.java)
                            context.startActivity(intent)
                        },
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = null,
                )
                Image(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(start = 135.dp),
                    painter = painterResource(id = R.drawable.cnn),
                    contentDescription = null,
                )
            }
        }
    }

    @Composable
    fun Detail() {
        Column(modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formatDate(selectedArticle?.isoDate.toString()),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF7F7F7F),
                )
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = selectedArticle?.title.toString(),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(10.dp)),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedArticle?.image?.large.toString())
                    .crossfade(false)
                    .build(),
                contentDescription = "image description",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                text = selectedArticle?.contentSnippet.toString(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Justify,
                )
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        selectedArticle?.link?.let {
                            openBrowser(it)
                        }
                    },
                text = "BACA SELENGKAPNYA",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFC60000),
                    textAlign = TextAlign.Justify,
                    textDecoration = TextDecoration.Underline,
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(39.dp)
                    .background(color = Color(0xFFF8C0C0), shape = RoundedCornerShape(size = 20.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .padding(0.dp)
                            .width(30.dp)
                            .height(30.dp),
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = null
                    )
                    Text(
                        text = "SIMPAN",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFC60000),
                            textAlign = TextAlign.Justify,
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .width(180.dp)
                    .height(39.dp)
                    .background(color = Color(0xFFF8C0C0), shape = RoundedCornerShape(size = 20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .padding(0.dp)
                            .width(23.dp)
                            .height(23.dp),
                        painter = painterResource(id = R.drawable.like),
                        contentDescription = null
                    )
                    Text(
                        text = "SUKA",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFC60000),
                            textAlign = TextAlign.Justify,
                        )
                    )
                }
            }
        }
    }

    private fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(this, intent, null)
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }
}