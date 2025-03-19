package kalbe.corp.shoppinglistapp

import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingAPIService {
    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latlg: String,
        @Query("key") apiKey: String,
    ): GeocodingResponse
}