package com.example.pruebamovilapp.ui.pdv

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.automirrored.filled.ArrowBack
// Sample Product model
data class Product(
    val name: String,
    var costPrice: String,
    var salePrice: String,
    var stock: String
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceReportScreen(navController: NavController, pdvName: String) {
    var productList by remember {
        mutableStateOf(
            listOf(
                Product("Aceite Clikx12 Bot", "45.23", "45.23", "100"),
                Product("Aceite Primorx12 Bot", "42.00", "45.23", "120"),
                Product("Aceite Saox12 Bot", "41.50", "45.23", "80"),
                Product("Aceite Idealx12 Bot", "41.50", "45.23", "30"),
                Product("Aceite Metrox12 Bot", "40.00", "45.23", "17"),
                Product("Aceite Bellsx12 Bot", "38.23", "45.23", "35")
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reporte Precios") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.popBackStack("pdv", inclusive = false)
                    }) {
                        Icon(Icons.Filled.Save, contentDescription = "Guardar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White
                )
            )
        }

    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Text(
                text = "Reporte Precios",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
                item {
                    Row(
                        Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Producto", Modifier.weight(2f), style = MaterialTheme.typography.labelLarge)
                        Text("P. Costo", Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
                        Text("P. Rvta", Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
                        Text("Stock", Modifier.weight(1f), style = MaterialTheme.typography.labelLarge)
                    }
                    Divider(thickness = 1.dp)
                }

                items(productList.size) { index ->
                    val product = productList[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(product.name, Modifier.weight(2f))
                        EditableCell(product.costPrice, Modifier.weight(1f)) { newValue ->
                            productList = productList.toMutableList().also {
                                it[index] = it[index].copy(costPrice = newValue)
                            }
                        }
                        EditableCell(product.salePrice, Modifier.weight(1f)) { newValue ->
                            productList = productList.toMutableList().also {
                                it[index] = it[index].copy(salePrice = newValue)
                            }
                        }
                        EditableCell(product.stock, Modifier.weight(1f)) { newValue ->
                            productList = productList.toMutableList().also {
                                it[index] = it[index].copy(stock = newValue)
                            }
                        }
                    }
                    Divider()
                }
            }

        }
    }
}

@Composable
fun EditableCell(value: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue(value)) }
    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onValueChange(it.text)
        },
        modifier = modifier.padding(horizontal = 4.dp)
    )
}
