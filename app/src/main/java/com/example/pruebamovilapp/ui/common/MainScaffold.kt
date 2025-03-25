package com.example.pruebamovilapp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import androidx.compose.material3.HorizontalDivider

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavController,
    title: String,
    showDrawer: Boolean = true,
    content: @Composable (Modifier) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (showDrawer) {
                ModalDrawerSheet {
                    Text("Antonio Herrera", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    Text("aherrera@lucky.com.pe", modifier = Modifier.padding(start = 16.dp, bottom = 16.dp))
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text("Puntos de Venta") },
                        selected = false,
                        onClick = {
                            navController.navigate("pdv")
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Cerrar sesión") },
                        selected = false,
                        onClick = {
                            navController.navigate("login") {
                                popUpTo(0)
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (showDrawer) {
                    TopAppBar(
                        title = { Text(title) },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = Color.White)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF2196F3),
                            titleContentColor = Color.White
                        )
                    )
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content(Modifier)
            }
        }
    }
}
