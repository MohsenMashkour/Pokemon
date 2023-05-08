package com.mkrdeveloper.pokemon.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkrdeveloper.pokemon.models.Result
import com.mkrdeveloper.pokemon.utils.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PokemonViewModel:ViewModel() {

    var pokemonListResponse:List<Result> by mutableStateOf(listOf())
    var errorMessage : String by mutableStateOf("")

    fun getPokemonList(){
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.api.getPokemonList()


            }catch (e: HttpException){

                errorMessage = e.message.toString()
                return@launch
            }
            if (response.isSuccessful && response.body() != null){
                pokemonListResponse = response.body()!!.results
            }
        }

    }
}

