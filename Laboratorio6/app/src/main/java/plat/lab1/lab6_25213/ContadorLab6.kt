//Esteban Sánchez 25213

package plat.lab1.lab6_25213

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import plat.lab1.lab6_25213.ui.theme.Lab6_25213Theme

@Composable
fun ContadorAvanzado(
    modifier: Modifier = Modifier
) {
    var contador by rememberSaveable { mutableIntStateOf(0) }
    var totalIncrementos by rememberSaveable { mutableIntStateOf(0) }
    var totalDecrementos by rememberSaveable { mutableIntStateOf(0) }
    var valorMaximo by rememberSaveable { mutableIntStateOf(0) }
    var valorMinimo by rememberSaveable { mutableIntStateOf(0) }
    var historial by remember { mutableStateOf(listOf<Pair<Int, Boolean>>()) }

    val filas = mutableListOf<List<Pair<Int, Boolean>>>()
    var i = 0
    while (i < historial.size) {
        val fin = if (i + 5 < historial.size) i + 5 else historial.size
        filas.add(historial.subList(i, fin))
        i += 5
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Esteban Sánchez",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilledTonalIconButton(
                onClick = {
                    contador--
                    totalDecrementos++
                    if (contador < valorMinimo) {
                        valorMinimo = contador
                    }
                    historial = historial + Pair(contador, false)
                }
            ) {
                Text("−")
            }
            Text(
                text = contador.toString(),
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            FilledTonalIconButton(
                onClick = {
                    contador++
                    totalIncrementos++
                    if (contador > valorMaximo) {
                        valorMaximo = contador
                    }
                    historial = historial + Pair(contador, true)
                }
            ) {
                Text("+")
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            FilaEstadistica("Total incrementos:", totalIncrementos)
            FilaEstadistica("Total decrementos:", totalDecrementos)
            FilaEstadistica("Valor máximo:", valorMaximo)
            FilaEstadistica("Valor mínimo:", valorMinimo)
            FilaEstadistica("Total cambios:", totalIncrementos + totalDecrementos)

            Text(
                text = "Historial:",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filas) { fila ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    fila.forEach { movimiento ->
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    color = if (movimiento.second) Color.Green else Color.Red,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        ) {
                            Text(
                                text = movimiento.first.toString(),
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }

        FilledTonalButton(
            onClick = {
                contador = 0
                totalIncrementos = 0
                totalDecrementos = 0
                valorMaximo = 0
                valorMinimo = 0
                historial = listOf()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Reiniciar")
        }
    }
}

@Composable
private fun FilaEstadistica(
    etiqueta: String,
    valor: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = etiqueta,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = valor.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewContadorAvanzado() {
    Lab6_25213Theme {
        Surface {
            ContadorAvanzado()
        }
    }
}

