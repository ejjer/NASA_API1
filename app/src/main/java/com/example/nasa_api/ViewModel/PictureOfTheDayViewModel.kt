package com.example.nasa_api.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasa_api.BuildConfig
import com.example.nasa_api.MainActivity
import com.example.nasa_api.Repository.PictureOfTheDayResponseData
import com.example.nasa_api.Repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class PictureOfTheDayViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()
                               ,private val repositoryImpl: RepositoryImpl=RepositoryImpl()):
    ViewModel() {

    fun getLiveData(): MutableLiveData<AppState>{

        return liveData
    }

    fun sendRequest(){
        liveData.postValue(AppState.Loading)
        repositoryImpl.getPictureOfTheDayAPI().getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(callback)
    }

    val callback  = object :Callback<PictureOfTheDayResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful){
                liveData.postValue(AppState.Success(response.body()!!))
            }
            else{
                liveData.postValue(AppState.Error(throw IllegalStateException("Произошла ошибка")))
            }
            response.body()!!
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable)  {
            liveData.postValue(AppState.Error(IllegalStateException("error")))
        }


    }
    }
