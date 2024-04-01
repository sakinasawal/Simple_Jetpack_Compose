package io.rapidz.jetpackcomposetraining_assignment0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import io.rapidz.jetpackcomposetraining_assignment0.repository.UserRepository
import io.rapidz.jetpackcomposetraining_assignment0.storage.AppDatabase
import io.rapidz.jetpackcomposetraining_assignment0.storage.SharedPreferences
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.JetpackComposeTrainingAssignment0Theme

class MainActivity : ComponentActivity() {

    lateinit var userRepository: UserRepository
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userDao = AppDatabase.getDatabase(applicationContext).userDao()
        userRepository = UserRepository(userDao)

        sharedPreferences = SharedPreferences(this)

        setContent {
            JetpackComposeTrainingAssignment0Theme {
                MainApplication()
            }
        }
    }
}

@Composable
fun MainApplication() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(
                userRepository = (LocalContext.current as MainActivity).userRepository,
                sharedPreferences = (LocalContext.current as MainActivity).sharedPreferences,
                navController)
        }
        composable("list") {
            ListScreen()
        }
    }
}