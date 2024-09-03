package com.example.quicknews.ui.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
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