//Esteban Sánchez 25213
package plat.lab1.laboratorio425213

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import plat.lab1.laboratorio425213.ui.theme.Laboratorio425213Theme
import androidx.compose.foundation.layout.Arrangement
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio425213Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PortadaLab425213(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PortadaLab425213(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .border(width = 5.dp, color = Color.Green)
            .padding(24.dp)
    ) {
        // Escudo de fondo
        Image(
            painter = painterResource(id = R.drawable.escudo_uvg),
            contentDescription = "Escudo UVG",
            modifier = Modifier
                .align(Alignment.Center)
                .size(220.dp)
                .alpha(0.15f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {

            // Fila 1: Nombre de la universidad
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Universidad del Valle de Guatemala",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Fila 2: Curso
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Programación de plataformas móviles, Sección 30",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Integrantes
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1.3f)) {
                    Text(text = "INTEGRANTES", fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier.weight(2f).padding(start = 46.dp)) {
                    Column {
                        Text("Esteban Sánchez")
                        Text("Javier Sánchez")
                        Text("Diego Rodriguez")
                    }
                }
            }

            // Catedrático
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.weight(1.3f)) {
                    Text(text = "CATEDRÁTICO", fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier.weight(2f).padding(start = 46.dp)) {
                    Text(text = "Juan Carlos Durini")
                }
            }

            // Nombre y carné
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Esteban Sánchez")
                    Text(text = "25213")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PortadaPreview() {
    Laboratorio425213Theme {
        PortadaLab425213()
    }
}