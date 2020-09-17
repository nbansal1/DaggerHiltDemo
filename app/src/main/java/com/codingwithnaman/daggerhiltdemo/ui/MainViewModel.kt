package com.codingwithnaman.daggerhiltdemo.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.codingwithnaman.daggerhiltdemo.model.Post
import com.codingwithnaman.daggerhiltdemo.repository.Repository
import com.codingwithnaman.daggerhiltdemo.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
)  : ViewModel(){

    private val _dataState : MutableLiveData<DataState<List<Post>>> = MutableLiveData()

    val dataState : LiveData<DataState<List<Post>>>
        get() = _dataState

    @ExperimentalCoroutinesApi
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when(mainStateEvent) {
                is MainStateEvent.GetPostEvent -> {
                    repository.getPosts()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }
                MainStateEvent.None -> {

                }
            }
        }
    }

}

sealed class MainStateEvent{
    object GetPostEvent : MainStateEvent()
    object None : MainStateEvent()
}