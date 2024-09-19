import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.example.tiktaktoe.R
import kotlin.math.min

@Composable
fun Board(
    size: Int,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Calcular el tamaño de la celda en función del tamaño mínimo disponible (ancho o alto)
        val cellSize = min(maxWidth, maxHeight) / size

        // Dibujar las líneas del tablero
        DrawGridLines(size = size, cellSize = cellSize)

        LazyVerticalGrid(
            columns = GridCells.Fixed(size),
            modifier = Modifier
                .size(cellSize * size) // Ajustar el tamaño del grid al del tablero
        ) {
            items(size * size) { item ->
                Cell(
                    value = 0,
                    size = cellSize,
                    onClick = { /* TODO*/ }
                )
            }
        }
    }
}

@Composable
fun DrawGridLines(size: Int, cellSize: Dp) {
    val cellSizePx = with(LocalDensity.current) { cellSize.toPx() }

    Canvas(modifier = Modifier.size(cellSize * size)) {
        // Líneas horizontales
        for (i in 1 until size) {
            drawLine(
                color = Color.Black,
                start = Offset(0f, i * cellSizePx),
                end = Offset(size * cellSizePx, i * cellSizePx),
                strokeWidth = 8f
            )
        }

        // Líneas verticales
        for (i in 1 until size) {
            drawLine(
                color = Color.Black,
                start = Offset(i * cellSizePx, 0f),
                end = Offset(i * cellSizePx, size * cellSizePx),
                strokeWidth = 8f
            )
        }
    }
}

@Composable
fun Cell(
    value: Int,
    size: Dp,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .size(size)
            .padding(8.dp),
        onClick = onClick
    ) {
        if (value == 1) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "cell"
            )
        } else if (value == 2) {
            Icon(
                Icons.Outlined.Info,
                contentDescription = "cell"
            )
        } else {
            Icon(
                Icons.Outlined.Favorite,
                contentDescription = "cell"
            )
        }
    }
}

@Composable
fun showPlayer(player1 : String, player2 : String){
    Row {
        Text(
            text = player1,
            color = colorResource(R.color.bluePure),
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleSmall

            )
                Spacer(modifier = Modifier.padding(8.dp))
        Text("x",
            color = colorResource(R.color.bluePure),
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleSmall
        )  

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = player2,
            color = colorResource(R.color.redPure),
            fontSize = 32.sp,
            style = MaterialTheme.typography.titleSmall
            )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "o",
            color = colorResource(R.color.redPure),
            fontSize = 32.sp,                          
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun alignedBoard(size: Int, player1: String, player2: String){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        showPlayer(player1 = player1, player2 = player2)
        Board(size = size)
    }
}

@Preview(showBackground = true)
@Composable
fun boardPreview() {
    alignedBoard(size = 3, player1 = "Jaime", player2 = "Pepe")
}
