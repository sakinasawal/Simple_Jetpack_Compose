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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.OrangeStart
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import io.rapidz.jetpackcomposetraining_assignment0.data.User
import io.rapidz.jetpackcomposetraining_assignment0.repository.UserRepository
import io.rapidz.jetpackcomposetraining_assignment0.storage.SharedPreferences
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.*
import kotlinx.coroutines.runBlocking

@Composable
fun LoginScreen(userRepository : UserRepository,
                sharedPreferences: SharedPreferences,
                navController: NavHostController
){
    // by remember : refresh one fun screen
    var username by remember { mutableStateOf(sharedPreferences.getLastLoginUsername() ?: "") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    val context = LocalContext.current

    // derivedStateOf : convert one or multiple state objects into another state
    val isLoginEnabled by remember(username, password) {
        derivedStateOf {
            username.isNotBlank() && password.isNotBlank()
        }
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = spacing_20)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.spacedBy(spacing_20),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.login),
            style = TextStyle(fontSize = font_size_24, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = spacing_20)
        )

        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(id = R.string.username)) },
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
            label = { Text(stringResource(id = R.string.password))},
            singleLine = true,
            shape = MaterialTheme.shapes.extraLarge,
            isError = errorText.isNotEmpty(),
            trailingIcon = {
                if (errorText.isNotEmpty()){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_exclamation_mark),
                        contentDescription = "Error",
                        tint = Color.Red
                    )
                }
            }
        )

        if (errorText.isNotEmpty()) {
            Text(
                text = errorText,
                color = Color.Red,
                modifier = Modifier.padding(top = spacing_4)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            if (password.length < 8) {
                errorText = context.getString(R.string.password_not_meet_requirement)
                username = ""
                password = ""
            } else {
                val user = runBlocking { userRepository.getUserByUsername(username) }
                if (user != null){
                    sharedPreferences.setLastLoginUsername(username)
                    if (user.password != password){
                        password = ""
                        errorText = context.getString(R.string.wrong_password)
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
                .height(spacing_50),
        ) {
            Text(
                text = stringResource(id = R.string.login),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


