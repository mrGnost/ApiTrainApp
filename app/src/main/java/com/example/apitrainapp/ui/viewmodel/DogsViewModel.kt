package com.example.apitrainapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitrainapp.data.model.DogSample
import com.example.apitrainapp.repository.DogsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(private val repository: DogsRepository) : ViewModel() {
    private val _dogsLiveData = MutableLiveData<DogSample>()
    val dogsLiveData: LiveData<DogSample>
        get() = _dogsLiveData

    private val _errorLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData

    fun getDog() {
        viewModelScope.launch {
            val data = repository.getDog()
            data.body()?.let { _dogsLiveData.postValue(it) }
        }
    }
}