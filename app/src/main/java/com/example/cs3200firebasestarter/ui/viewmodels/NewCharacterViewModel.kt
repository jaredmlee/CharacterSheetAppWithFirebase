package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.CharacterRepository
import com.example.cs3200firebasestarter.ui.repositories.UserRepository

class NewCharacterScreenState {
    var name by mutableStateOf("")
    var age by mutableStateOf("")
    var race by mutableStateOf("")
    var characterClass by mutableStateOf("" )
    var height by mutableStateOf("")
    var gender by mutableStateOf("")
    var description by mutableStateOf("")
}

class NewCharacterViewModel(application: Application): AndroidViewModel(application) {
    val uiState = NewCharacterScreenState()
    var id: String? = null
    suspend fun saveCharacter(){
        if (id == null){
            CharacterRepository.createCharacter(
                name = uiState.name,
                age = uiState.age,
                race = uiState.race,
                characterClass = uiState.characterClass,
                height = uiState.height,
                gender = uiState.gender,
                description = uiState.description
            )
        }
        else{
            val character = CharacterRepository.getCharacter().find{ it.id == id  } ?: return
            CharacterRepository.updateCharacter(
                character.copy(
                    name = uiState.name,
                    description = uiState.description,
                    age = uiState.age,
                    characterClass = uiState.characterClass,
                    gender = uiState.gender,
                    height = uiState.height,
                    race = uiState.race
                )
            )
        }
    }

    suspend fun setUpInitialState(id: String?){
        if (id == null || id == "new") return
        this.id = id
        val character = CharacterRepository.getCharacter().find{ it.id == id  } ?: return
        uiState.name = character.name ?: ""
        uiState.description =  character.description ?: ""
        uiState.age = character.age ?: ""
        uiState.characterClass = character.characterClass ?: ""
        uiState.gender = character.gender ?: ""
        uiState.height = character.height ?: ""
        uiState.race = character.race ?: ""

    }

}