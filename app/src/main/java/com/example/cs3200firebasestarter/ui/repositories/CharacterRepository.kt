package com.example.cs3200firebasestarter.ui.repositories

import com.example.cs3200firebasestarter.ui.models.Character
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object CharacterRepository {

    private val charactersCache = mutableListOf<Character>()
    private var cacheInitialized = false

    suspend fun createCharacter(
        name : String,
        age: String,
        race: String,
        characterClass: String,
        height: String,
        gender: String,
        description: String
        ) : Character{
            val doc =  Firebase.firestore.collection("characters").document()
            val character = Character(
                name = name,
                age = age,
                characterClass = characterClass,
                height = height,
                gender = gender,
                description = description,
                race = race,
                id = doc.id,
                userId = UserRepository.getCurrentUserId()
            )
            doc.set(character).await()
            charactersCache.add(character)
            return character
    }

    suspend fun getCharacter() : List<Character>{
        if (!cacheInitialized){
            val snapshot = Firebase.firestore
                .collection("characters")
                .whereEqualTo("userId", UserRepository.getCurrentUserId())
                .get()
                .await()
            charactersCache.addAll(snapshot.toObjects())
            cacheInitialized = true
        }
        return charactersCache
    }

    suspend fun updateCharacter(character: Character){
        Firebase.firestore
            .collection("characters")
            .document(character.id!!)
            .set(character)
            .await()
        val oldCharacter = charactersCache.indexOfFirst {
            it.id == character.id
        }
        charactersCache[oldCharacter] = character
    }

}