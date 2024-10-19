package com.example.fetchreward.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fetchreward.room.ItemEntity
import com.example.fetchreward.viewModel.retrofitApi.MainViewModel

@Composable
fun ExpandableListScreen(viewModel: MainViewModel) {

    val groupedItems by viewModel.items.observeAsState(emptyMap())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedItems.forEach { (listID, items) ->
            item {
                ExpandableListItem(
                    listID = listID,
                    items = items
                )
            }
        }
    }
}

@Composable
fun ExpandableListItem(listID: Int, items: List<ItemEntity>) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize() // Smooth animation when expanding/collapsing
    ) {
        Text(
            text = "List ID: $listID",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded // Toggle expanded/collapsed state
                }
                .padding(16.dp)
        )

        if (isExpanded) {
            items.forEachIndexed  { index, item ->
                Column(modifier = Modifier.padding(start = 32.dp, top = 4.dp, bottom = 4.dp)) {
                    Text(
                        text = "ID: ${item.id}",
                        style = MaterialTheme.typography.body2, // Use a different style if needed
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Name: ${item.name}",
                        style = MaterialTheme.typography.body1, // Use the same or a different style
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Add a Divider after each item, except the last one
                if (index < items.size - 1) {
                    Divider(
                        color = Color.Gray, // You can customize the color here
                        thickness = 1.dp, // Customize the thickness here
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}