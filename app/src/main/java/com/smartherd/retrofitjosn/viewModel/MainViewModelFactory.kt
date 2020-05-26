package com.smartherd.retrofitjosn.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smartherd.retrofitjosn.repository.PlaceholderRepository

class MainViewModelFactory(
    val repository: PlaceholderRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}