package com.example.fitsnap.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitsnap.Models.NutritionInfoScaled
import com.example.fitsnap.Repositories.FeedRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FeedViewModel @Inject constructor(private val repo:FeedRepo) : ViewModel() {
    private val _foodName = MutableLiveData<String>()
    val foodName : LiveData<String>
        get() = _foodName

    private val _healthScore = MutableLiveData<String>()
    val healthScore : LiveData<String>
        get() = _healthScore

    private val _description = MutableLiveData<String>()
    val description : LiveData<String>
        get() = _description
    private val _nutritionScale = MutableLiveData<List<NutritionInfoScaled>>()
    val nutritionScale : LiveData<List<NutritionInfoScaled>>
        get() = _nutritionScale

    private val _facts = MutableLiveData<List<String>>()
    val facts : LiveData<List<String>>
        get() = _facts

    fun getFeed(){
        viewModelScope.launch (Dispatchers.IO){
           val result = viewModelScope.async(Dispatchers.IO) {
                repo.getFeed()
            }

            _foodName.postValue(result.await().data.name)
            _healthScore.postValue(result.await().data.health_rating.toString())
            _description.postValue(result.await().data.description)
            _nutritionScale.postValue(result.await().data.nutrition_info_scaled)
            _facts.postValue(result.await().data.generic_facts)

        }
    }
}