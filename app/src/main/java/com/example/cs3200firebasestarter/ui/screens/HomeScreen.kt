package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.models.Character
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.viewmodels.HomeViewModel

@Composable
fun CharacterListItem(character: Character, navHostController: NavHostController){
    Column {
        Box (
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .size(150.dp)){
            Row( modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text(text = character.name.toString(), fontSize = 25.sp)
                    Text(text = character.race.toString())
                    Text(text = character.characterClass.toString())
                }
                Column (horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()){
                    Button(onClick = { navHostController.navigate("editcharacter?id=${character.id}") }) {
                        Text(text = "Edit")
                    }
                }
            }
        }
        Divider(color = Color.Blue, thickness = 2.dp)
    }
}

@Composable
fun HomeScreen(navHostController: NavHostController) {
    val viewModel : HomeViewModel = viewModel()
    val state = viewModel.uiState
    LaunchedEffect(true){
        viewModel.getCharacters()
        println("LaunchedEffect")
    }
    LazyColumn{
        items(state.characters, key = {it.id!!}){
            CharacterListItem(character = it, navHostController)
        }
    }

}

    