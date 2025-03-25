package com.example.pruebamovilapp.ui.pdv

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pruebamovilapp.R
import com.example.pruebamovilapp.ui.common.MainScaffold

// Data class for PDV items
data class PDV(
    val name: String,
    val code: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)

@Composable
fun PDVScreen(navController: NavController) {
    val pdvList = remember {
        listOf(
            PDV("YOSLY AMALI SEGUILAR", "409183", "REAL S/N Urb: ESQ.10 NOVIEMBRE", -12.0464, -77.0428),
            PDV("METRO ALFONSO UGARTE", "409184", "AV. ALFONSO UGARTE 740", -12.0450, -77.0300),
            PDV("TOTTUS ZORRITOS", "409183", "AV. COLONIAL 1520", -12.0490, -77.0330)
        )
    }

    MainScaffold(
        navController = navController,
        title = "Punto de Venta"
    ) { modifier ->
        LazyColumn(modifier = modifier) {
            items(pdvList) { pdv ->
                PDVCard(pdv = pdv, navController = navController)
            }
        }
    }
}

@Composable
fun PDVCard(pdv: PDV, navController: NavController) {
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(pdv.name) },
            text = { Text("¿Atenderá el pdv?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog.value = false
                    navController.navigate("visit/${pdv.name}/${pdv.address}")
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text("No")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_maps_logo),
                contentDescription = "Google Maps Icon",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        navController.navigate("map")
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = pdv.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Código: ${pdv.code}")
                Text(text = pdv.address)
            }
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Ir a visita",
                modifier = Modifier.clickable { showDialog.value = true },
                tint = Color.Gray
            )
        }
    }
}
