package com.example.tiktaktoe

import Board
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tiktaktoe.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.tiktaktoe.views.home
import android.util.Log

import com.example.tiktaktoe.ui.theme.TiktaktoeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var user1 by remember { mutableStateOf("") }
            var user2 by remember { mutableStateOf("") }
            var boardSize by remember { mutableStateOf(0) }
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorResource(R.color.white)
            ) {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "homeScreen" ) {
                    composable(route = "homeScreen") {
                        home(
                            onUser1Changed = { user1 = it },
                            onUser2Changed = { user2 = it },
                            onBoardSizeSelected = { size ->
                                if (size > 0) {
                                    boardSize = size
                                    navController.navigate("boardScreen/$size")
                                }
                            }
                        )
                    }
                    composable(route = "boardScreen/{boardSize}") { backStackEntry ->
                        val boardSize = backStackEntry.arguments?.getString("boardSize")?.toInt() ?: 0
                        Board(size = boardSize)
                    }
                }
            }
        }
    }
}

@Composable
fun appTikTakToe(){
    var user1 by remember { mutableStateOf("") }
    var user2 by remember { mutableStateOf("") }
    var boardSize by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(R.color.white)
    ) {

        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "homeScreen" ) {
            composable(route = "homeScreen") {
                home(
                    onUser1Changed = { user1 = it },
                    onUser2Changed = { user2 = it },
                    onBoardSizeSelected = { size ->
                        if (size > 0) {
                            boardSize = size
                            navController.navigate("boardScreen/$size")
                        }
                    }
                )
            }
            composable(route = "boardScreen/{boardSize}") { backStackEntry ->
                val boardSize = backStackEntry.arguments?.getString("boardSize")?.toInt() ?: 0
                Board(size = boardSize)
            }
        }
    }
}

@Preview
@Composable
fun previewapp(){
    appTikTakToe()
}