package com.example.quicknews.ui.screens.home.article_details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.quicknews.R

@Composable
fun ArticleDetailsScreen(
    viewModel: ArticleDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ArticleDetailsContent(state = state)
}

@Composable
private fun ArticleDetailsContent(
    modifier: Modifier = Modifier,
    state: ArticleDetailsUiState
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            painter = rememberAsyncImagePainter(model = state.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = state.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W700
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.width(200.dp),
                text = state.author,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = state.publishedAt,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = state.content,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActionButton(
            onClick = {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(state.url)
                    )
                )
            },
            text = stringResource(R.string.continue_reading)
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActionButton(
            onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, state.url)
                        type = "text/plain"
                    }
                )
            },
            text = stringResource(R.string.share)
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(bottom = 12.dp, top = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF846756)),
        onClick = onClick
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
        )
    }
}