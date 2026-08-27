//Esteban Sánchez  25213
package plat.lab5_25213


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import plat.lab5_25213.ui.theme.Lab5_25213Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab5_25213Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HorarioRestaurante(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun HorarioRestaurante(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(modifier = modifier) {

        BannerActualizacion(
            onDescargarClick = { abrirPlayStore(context, "com.whatsapp") }
        )

        Column(modifier = Modifier.padding(20.dp)) {

            EncabezadoFecha()

            Spacer(modifier = Modifier.height(24.dp))

            TarjetaRestaurante(
                onDireccionesClick = {
                    abrirGoogleMaps(
                        context = context,
                        lat = 14.60059,
                        lng = -90.51287,
                        etiqueta = "Los Cebollines"
                    )
                },
                onIniciarClick = {
                    Toast.makeText(context, "Esteban Sánchez", Toast.LENGTH_SHORT).show()
                },
                onDetallesClick = {
                    Toast.makeText(
                        context,
                        "Comida mexicana\n (Precio: Q75)",
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
        }
    }
}

@Composable
private fun BannerActualizacion(onDescargarClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "↻",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Actualización disponible",
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        TextButton(onClick = onDescargarClick) {
            Text("Descargar")
        }
    }
}

@Composable
private fun EncabezadoFecha() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Text(
                text = "Miércoles",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "29 de julio",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        OutlinedButton(onClick = { }) {
            Text("Terminar jornada")
        }
    }
}

@Composable
private fun TarjetaRestaurante(
    onDireccionesClick: () -> Unit,
    onIniciarClick: () -> Unit,
    onDetallesClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Los Cebollines",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onDireccionesClick) {
                Text(
                    text = "➜",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Text(
            text = "Paseo Cayalá, Zona 16",
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "6:30AM - 10:00PM",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onIniciarClick) {
                Text("Iniciar")
            }
            TextButton(onClick = onDetallesClick) {
                Text("Detalles")
            }
        }
    }
}

private fun abrirPlayStore(context: Context, packageName: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
    )
    context.startActivity(intent)
}

private fun abrirGoogleMaps(context: Context, lat: Double, lng: Double, etiqueta: String) {
    val gmmIntentUri = Uri.parse("geo:$lat,$lng?q=$lat,$lng(${Uri.encode(etiqueta)})")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
        setPackage("com.google.android.gms.maps")
    }
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/?q=$lat,$lng"))
        context.startActivity(browserIntent)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHorarioRestaurante() {
    Lab5_25213Theme {
        Surface {
            HorarioRestaurante(modifier = Modifier.fillMaxSize())
        }
    }
}