package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.models.Character
import com.example.cs3200firebasestarter.ui.repositories.CharacterRepository

class CharacterScreenState{
    val _characters = mutableStateListOf<Character>()
    val characters: List<Character> get() = _characters
}


class HomeViewModel(application: Application): AndroidViewModel(application) {
    val uiState = CharacterScreenState()
    suspend fun getCharacters() {
        println("get characters viewmodel")
        val characters =  CharacterRepository.getCharacter()
        uiState._characters.clear()
        uiState._characters.addAll(characters)
    }
}