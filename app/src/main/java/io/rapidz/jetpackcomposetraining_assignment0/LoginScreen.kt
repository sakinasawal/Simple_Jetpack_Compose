package io.rapidz.jetpackcomposetraining_assignment0

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.OrangeStart
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import io.rapidz.jetpackcomposetraining_assignment0.data.User
import io.rapidz.jetpackcomposetraining_assignment0.repository.UserRepository
import io.rapidz.jetpackcomposetraining_assignment0.storage.SharedPreferences
import kotlinx.coroutines.runBlocking

@Composable
fun LoginScreen(userRepository : UserRepository,
                sharedPreferences: SharedPreferences,
                navController: NavHostController
){

    var username by remember { mutableStateOf(sharedPreferences.getLastLoginUsername() ?: "") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val isLoginEnabled by remember(username, password) {
        derivedStateOf {
            username.isNotBlank() && password.isNotBlank()
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Login ",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 20.dp)
        )

        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions(onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            shape = MaterialTheme.shapes.extraLarge,
        )

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password")},
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            isError = errorText.isNotEmpty()
        )

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            if (password.length < 8) {
                errorText = "The password does not meet the requirement."
                username = ""
                password = ""
            } else {
                val user = runBlocking { userRepository.getUserByUsername(username) }
                if (user != null){
                    sharedPreferences.setLastLoginUsername(username)
                    if (user.password != password){
                        password = ""
                        errorText = "Wrong password"
                    } else {
                        navController.navigate("list")
                    }
                }else {
                    runBlocking {
                        userRepository.insertUser(User(username = username, password = password))
                        sharedPreferences.setLastLoginUsername(username)
                        navController.navigate("list")
                    }
                }
            }
            focusManager.clearFocus()
        },
            enabled = isLoginEnabled,
            colors = ButtonDefaults.buttonColors(containerColor = OrangeStart),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


