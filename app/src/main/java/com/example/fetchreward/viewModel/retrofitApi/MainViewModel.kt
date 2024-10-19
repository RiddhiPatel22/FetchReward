package com.example.fetchreward.viewModel.retrofitApi

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchreward.repository.ItemRepository
import com.example.fetchreward.room.AppDatabase
import com.example.fetchreward.room.ItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val app: Application) : ViewModel() {

    private val _items = MutableLiveData<Map<Int, List<ItemEntity>>>()
    val items: LiveData<Map<Int, List<ItemEntity>>> = _items

    private val itemRepository: ItemRepository
    lateinit var allItem: List<ItemEntity>

    init {
        val itemDao = AppDatabase.getDatabase(app).itemDao()
        itemRepository = ItemRepository(itemDao)
        viewModelScope.launch {
            insertAll()
        }
    }

    private fun insertAll(){
        viewModelScope.launch(Dispatchers.IO) {
            allItem = itemRepository.allItems()
            itemRepository.getListItems()
            _items.postValue(allItem.groupBy{it.listId})
        }
    }
}