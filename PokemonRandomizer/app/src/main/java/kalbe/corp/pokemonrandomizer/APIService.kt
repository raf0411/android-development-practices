package kalbe.corp.pokemonrandomizer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://pokeapi.co/api/v2/"
const val LIMIT = 20

private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val pokemonService: APIService = retrofit.create(APIService::class.java)

interface APIService{
    @GET("pokemon?limit=$LIMIT/")
    suspend fun getPokemonLists() : PokemonResponse

    @GET("pokemon/{id}/")
    suspend fun getSinglePokemon(
        @Path("id") id: Int
    ) : SinglePokemonResponse
}