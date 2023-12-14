package com.D121211027.newsest.ui.activities

import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211027.newsest.R
import com.D121211027.newsest.activities.MainActivity
import com.D121211027.newsest.activities.NewsActivity
import com.D121211027.newsest.data.models.Article
import com.D121211027.newsest.ui.theme.NewsestTheme
import java.text.SimpleDateFormat
import java.util.Locale

class PortalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsestTheme {
                Surface(
                    color = Color.White
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        BarPortal()
                        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)
                        ) {
                            val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                            ListNewsScreen(mainViewModel.mainUiState)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BarPortal() {
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
                            val intent = Intent(context, MainActivity::class.java)
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
    private fun ListNewsScreen(mainUiState: MainUiState) {
        when(mainUiState) {
            is MainUiState.Success -> ListNews(mainUiState.news)
            is MainUiState.Error -> ErrorText()
            is MainUiState.Loading -> LoadingBar()
        }
    }

    @Composable
    private fun ErrorText() {
        Text(text = "ERROR")
    }

    @Composable
    fun LoadingBar() {
        Text(text = "LOADING")
    }

    @Composable
    private fun ListNews(data: List<Article>, modifier: Modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = modifier) {
            items(data) { article ->
                News(article = article)
            }
        }
    }

    @Composable
    private fun News(article: Article) {
        Box(modifier = Modifier
            .clickable {
                val intent = Intent(this, NewsActivity::class.java)
                intent.putExtra("ARTICLE", article)
                startActivity(intent)
            }
            .fillMaxWidth()
            .height(120.dp)
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .width(95.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(article.image?.large)
                        .crossfade(false)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop
                )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = article.title ?: "Judul Berita",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                        )
                    )
                    Text(
                        modifier = Modifier
                            .width(153.dp)
                            .height(15.dp),
                        text = formatDate(article.isoDate.toString()),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF7F7F7F),
                        )
                    )
                }
            }
        }
    }

    private fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    }
}