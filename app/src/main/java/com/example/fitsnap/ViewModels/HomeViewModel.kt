package com.example.fitsnap.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitsnap.Repositories.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:HomeRepo) : ViewModel(){
    private val _goalName = MutableLiveData<String>()
    val goalName : LiveData<String>
        get() = _goalName
    private val _calories = MutableLiveData<String>()
    val calories : LiveData<String>
        get() = _calories
    private val _exercisePts = MutableLiveData<String>()
    val exercisePts : LiveData<String>
        get() = _exercisePts
    private val _percent = MutableLiveData<String>()
    val percent : LiveData<String>
        get() = _percent
    private val _percentage = MutableLiveData<String>()

    fun getHome(){

        viewModelScope.launch (Dispatchers.IO){
            val result = viewModelScope.async (Dispatchers.IO){
                repo.getHome()
            }
            _goalName.postValue(result.await().data.section_1.plan_name)
            _calories.postValue(result.await().data.section_2.calories_burned.toString())
            _exercisePts.postValue(result.await().data.section_2.reps.toString())
            _percent.postValue(result.await().data.section_1.progress)

        }
    }
}