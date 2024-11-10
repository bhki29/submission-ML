package com.dicoding.asclepius.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.data.local.room.HistoryDao

class HistoryRepository private constructor(
    private val cancerDao: HistoryDao,
) {

    fun getCancers(): LiveData<List<HistoryEntity>> = cancerDao.getCancers()

    suspend fun insertCancers(cancers: List<HistoryEntity>) {
        cancerDao.insertCancer(cancers)
    }

    suspend fun deleteCancer(cancer: HistoryEntity) {
        cancerDao.delete(cancer)
    }

    companion object {
        @Volatile
        private var instance: HistoryRepository? = null
        fun getInstance(
            cancerDao: HistoryDao
        ): HistoryRepository =
            instance ?: synchronized(this) {
                instance ?: HistoryRepository(cancerDao)
            }.also { instance = it }
    }
}