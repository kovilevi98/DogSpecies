package hu.bme.aut.dogspecies.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.dogspecies.ui.about.AboutScreen
import hu.bme.aut.dogspecies.ui.add.AddScreen
import hu.bme.aut.dogspecies.ui.add.AddViewModel
import hu.bme.aut.dogspecies.ui.list.ListScreen
import hu.bme.aut.dogspecies.ui.list.ListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: ListViewModel by viewModels()
    private lateinit var navController: NavHostController

    private val addViewModel: AddViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation()
        }
    }

    @Composable
    fun Navigation() {
        navController = rememberNavController()
        NavHost(navController, startDestination = "list") {
            composable(route = "list") {
                ListScreen(navController = navController, viewModel = viewModel, addViewModel = addViewModel).Screen()
            }
            composable(route = "About") {
                AboutScreen(navController = navController).Screen()
            }
            composable(route = "Plus") {
                AddScreen(
                    navController = navController,
                    addViewModel = addViewModel,
                ).Screen()
            }
        }
    }

}
