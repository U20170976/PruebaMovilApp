package com.example.pruebamovilapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pruebamovilapp.viewmodel.LoginViewModel
import com.example.pruebamovilapp.R

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val vm: LoginViewModel = viewModel()
    val success by vm.loginSuccess.collectAsState()
    val errorMessage by vm.errorMessage.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (success) onLoginSuccess()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "APP GESTOR",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Xplora")
        Text(text = "v.2.0.0", fontSize = 12.sp)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }


        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = { vm.login(username, password) }) {
            Text("Ingresar")
        }
    }
}

