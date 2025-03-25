package com.example.pruebamovilapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pruebamovilapp.ui.theme.PruebaMovilAppTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pruebamovilapp.ui.home.HomeScreen
import com.example.pruebamovilapp.ui.login.LoginScreen
import com.example.pruebamovilapp.ui.map.MapScreen
import com.example.pruebamovilapp.ui.pdv.PDVScreen
import com.example.pruebamovilapp.ui.pdv.VisitScreen
import com.example.pruebamovilapp.ui.pdv.PriceReportScreen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaMovilAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen { navController.navigate("pdv") }
                    }

                    composable("pdv") {
                        PDVScreen(navController)
                    }

                    composable("map") {
                        MapScreen(navController = navController)
                    }
                    composable(
                        "visit/{name}/{address}",
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType },
                            navArgument("address") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name") ?: ""
                        val address = backStackEntry.arguments?.getString("address") ?: ""
                        VisitScreen(name, address,navController)
                    }

                    composable(
                        "report/{pdvName}",
                        arguments = listOf(navArgument("pdvName") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val pdvName = backStackEntry.arguments?.getString("pdvName") ?: ""
                        PriceReportScreen(navController, pdvName)
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    androidx.compose.material3.Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PruebaMovilAppTheme {
        Greeting("Android")
    }
}


