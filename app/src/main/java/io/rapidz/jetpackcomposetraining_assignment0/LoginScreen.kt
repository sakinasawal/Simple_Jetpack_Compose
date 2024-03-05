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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import io.rapidz.jetpackcomposetraining_assignment0.ui.theme.OrangeStart

@Composable
fun LoginScreen(){

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isError by remember { mutableStateOf(false) }

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
                    // Hide the keyboard when the user taps outside the input fields
                    keyboardController?.hide()
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
            shape = MaterialTheme.shapes.extraLarge,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ }),
            modifier = Modifier.fillMaxWidth()
        )

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            shape = MaterialTheme.shapes.extraLarge,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ }),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* Handle login click */ },
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

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    modifier: Modifier = Modifier
) {
    val textFieldModifier = Modifier.fillMaxWidth()
    val keyboardOptions = KeyboardOptions.Default
    val keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        shape = MaterialTheme.shapes.extraLarge,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier.then(textFieldModifier) // Apply both modifiers
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
