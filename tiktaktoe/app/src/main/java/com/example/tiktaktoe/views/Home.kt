package com.example.tiktaktoe.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
fun Home(
    user1: String,
    user2: String,
    onBoardSizeSelected: (Int) -> Unit,
    onTextFieldChange1: (String) -> Unit,
    onTextFieldChange2: (String) -> Unit
){

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            fontSize = 48.sp,
            text="Tik-Tak-Toe",
            color = MaterialTheme.colorScheme.tertiary,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(64.dp))
        OutlinedTextField(
            value = user1,
            onValueChange = {onTextFieldChange1(it)},
            label = { Text("Usuario 1")},
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = user2,
            onValueChange = {onTextFieldChange2(it)},
            label = { Text("Usuario 2")},
        )
        Spacer(modifier = Modifier.size(32.dp))
        Row {
            FilledTonalButton(
                onClick = { onBoardSizeSelected(3) },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("3x3")
            }
            Spacer(modifier = Modifier.size(8.dp))
            FilledTonalButton(
                onClick = { onBoardSizeSelected(4) },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            )  {
                Text("4x4")
            }
            Spacer(modifier = Modifier.size(8.dp))
            FilledTonalButton(
                onClick = { onBoardSizeSelected(5) },
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            )  {
                Text("5x5")
            }
        }

    }
}


