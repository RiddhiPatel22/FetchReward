package com.example.fetchreward.repository

import com.example.fetchreward.room.ItemDao
import com.example.fetchreward.room.ItemEntity
import com.example.fetchreward.viewModel.retrofitApi.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ItemRepository(private val itemDao: ItemDao) {

    fun allItems(): List<ItemEntity> = itemDao.getAllItems()

    suspend fun getListItems(){
        val response = ApiService.getInstance().getItems()
        withContext(Dispatchers.IO){
            itemDao.insertAll(response)
        }
    }
}