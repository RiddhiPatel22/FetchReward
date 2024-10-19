package com.example.fetchreward.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey
    val id: Int,
    val listId: Int,
    val name: String?
)
