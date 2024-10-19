package com.example.fetchreward.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<ItemEntity>)

    @Query("SELECT * FROM itementity WHERE name IS NOT NULL AND name != '' ORDER BY listId ASC, name ASC")
    fun getAllItems() : List<ItemEntity>

}