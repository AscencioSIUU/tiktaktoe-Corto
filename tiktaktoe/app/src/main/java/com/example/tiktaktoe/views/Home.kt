package com.example.tiktaktoe.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiktaktoe.R

@Composable
fun home(
    onUser1Changed: (String) -> Unit,
    onUser2Changed: (String) -> Unit,
    onBoardSizeSelected: (Int) -> Unit,
){
    var user1 by remember { mutableStateOf("Kelevrinbor") }
    var user2 by remember { mutableStateOf("Filiberto") }

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            fontSize = 32.sp,
            text="Tik-Tak-Toe",
            color = colorResource(R.color.skyBueTikTakToe),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(64.dp))
        OutlinedTextField(
            value = user1,
            onValueChange = {
                user1 = it
                onUser1Changed(it)
            },
            label = { Text("Usuario 1")}
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = user2,
            onValueChange = {
                user2 = it
                onUser2Changed(it)
            },
            label = { Text("Usuario 2")}
        )
        Spacer(modifier = Modifier.size(16.dp))
        FilledTonalButton(
            onClick = { onBoardSizeSelected(3) },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(R.color.bluePure),
                contentColor = colorResource(R.color.white)
            )
        ) {
            Text("3x3")
        }
        Spacer(modifier = Modifier.size(8.dp))
        FilledTonalButton(
                onClick = { onBoardSizeSelected(4) },
                colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(R.color.redPure),
                contentColor = colorResource(R.color.white)
            )
        )  {
            Text("4x4")
        }
        Spacer(modifier = Modifier.size(8.dp))
        FilledTonalButton(
            onClick = { onBoardSizeSelected(5) },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(R.color.bluePure),
                contentColor = colorResource(R.color.white)
            )
        )  {
            Text("5x5")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewHome(){
    var user1 by remember { mutableStateOf("") }
    var user2 by remember { mutableStateOf("") }
    var boardSize by remember { mutableStateOf(3) }
    home(
        onUser1Changed = { user1 = it },
        onUser2Changed = { user2 = it },
        onBoardSizeSelected = { boardSize = it }
    )
}
