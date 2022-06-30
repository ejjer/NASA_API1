package com.example.nasa_api.ViewModel

import com.example.nasa_api.Repository.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData):AppState()
    data class Error(val error: Throwable):AppState()
    object Loading :AppState()


}