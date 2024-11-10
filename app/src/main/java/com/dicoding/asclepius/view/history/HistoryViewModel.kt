package com.dicoding.asclepius.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    val cancers: LiveData<List<HistoryEntity>> = repository.getCancers()


    fun insertCancers(cancers: List<HistoryEntity>) = viewModelScope.launch {
        repository.insertCancers(cancers)
    }

    fun deleteCancer(cancer: HistoryEntity) = viewModelScope.launch {
        repository.deleteCancer(cancer)
    }
}