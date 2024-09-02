package com.example.quicknews.ui.screens.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryItem(
    title: String,
    isSelected: Boolean = false,
    onSelected: (String) -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF1650C2) else Color(0xFFFAFAFA),
        label = ""
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else Color.Black, label = ""
    )

    val modifier = if (isSelected) Modifier.border(
        width = 1.dp,
        color = Color(0xFFF2F2F2),
        shape = RoundedCornerShape(8.dp)
    ) else Modifier

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable { onSelected(title) }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.W400,
            color = textColor,
            fontSize = 18.sp,
        )
    }
}