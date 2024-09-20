package com.example.tiktaktoe.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun WinnerView(
    winner: String,
    onButtonClick: () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .clickable { onButtonClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "El ganador es $winner", fontSize = 32.sp)
    }
}

//@Preview
//@Composable
//funn WinnerPreview(){
   // WinnerView("Kelebrimbor")
//}