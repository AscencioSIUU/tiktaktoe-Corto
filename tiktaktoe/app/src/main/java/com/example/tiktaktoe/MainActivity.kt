package com.example.tiktaktoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.tiktaktoe.views.Home
import com.example.tiktaktoe.views.AlignedBoard
import androidx.compose.material3.MaterialTheme
import com.example.tiktaktoe.ui.theme.MyAppTheme
import com.example.tiktaktoe.viewmodels.BoardViewModel
import com.example.tiktaktoe.views.WinnerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                AppTikTakToe()
            }

        }
    }
}

@Composable
fun AppTikTakToe(){
    var user1 by remember { mutableStateOf("") }
    var user2 by remember { mutableStateOf("") }
    var boardSize by remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {



        NavHost(navController = navController, startDestination = "homeScreen" ) {

            composable(route = "homeScreen") {
                Home(
                    user1 = user1,
                    user2 = user2,

                    onTextFieldChange1 = {value -> user1 = value},
                    onTextFieldChange2 = {value ->  user2= value},
                    onBoardSizeSelected = {
                            size ->
                        if (size > 0) {
                            boardSize = size
                            navController.navigate("boardScreen/$size/$user1/$user2")
                        }
                    }
                )
            }
            composable(route = "boardScreen/{boardSize}/{user1}/{user2}") { backStackEntry ->
                boardSize = backStackEntry.arguments?.getString("boardSize")?.toInt() ?: 0
                user1 = backStackEntry.arguments?.getString("user1") ?: "Guest 1"
                user2 = backStackEntry.arguments?.getString("user2") ?: "Guest 2"
                val initialPlayer = (1..2).random()
                val boardViewModel = BoardViewModel(boardSize, initialPlayer)

                AlignedBoard(
                    boardViewModel = boardViewModel,
                    size = boardSize,
                    player1 = user1,
                    player2 = user2,
                    onWin = {navController.navigate("winnerScreen/${if(boardViewModel.gameStatus == 1) user1 else user2}")})
            }

            composable(route = "winnerScreen/{winner}") { backStackEntry ->
                val winner = backStackEntry.arguments?.getString("winner") ?: "Guest1"

                WinnerView(
                    winner = winner
                ){
                    navController.navigate("homeScreen")
                }
            }
        }
    }
}

@Preview
@Composable
fun Previewapp(){
    MyAppTheme {
        AppTikTakToe()
    }

}