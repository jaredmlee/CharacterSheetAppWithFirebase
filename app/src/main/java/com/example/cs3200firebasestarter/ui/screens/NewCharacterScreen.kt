package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.viewmodels.NewCharacterViewModel
import kotlinx.coroutines.launch

@Composable
fun NewCharacterScreen(navHostController: NavHostController, id: String?){
    val viewModel: NewCharacterViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val state = viewModel.uiState
    LaunchedEffect(true){
        viewModel.setUpInitialState(id)
    }
    Column {
        FormField(
            value = state.name,
            onValueChange = { state.name = it },
            placeholder = { Text("Name") }
        )
        FormField(
            value = state.age,
            onValueChange = { state.age = it },
            placeholder = { Text("Age") }
        )
        FormField(
            value = state.race,
            onValueChange = { state.race = it },
            placeholder = { Text("Race") }
        )
        FormField(
            value = state.characterClass,
            onValueChange = { state.characterClass = it },
            placeholder = { Text("Class") }
        )
        FormField(
            value = state.height,
            onValueChange = { state.height = it },
            placeholder = { Text("Height") }
        )
        FormField(
            value = state.gender,
            onValueChange = { state.gender = it },
            placeholder = { Text("Gender") }
        )
        FormField(
            value = state.description,
            onValueChange = { state.description = it },
            placeholder = { Text("Description") }
        )
        Button(onClick = { scope.launch { viewModel.saveCharacter(); navHostController.navigate(
            Routes.home.route) } }) {
            Text(text = "Confirm")
        }
    }
}
