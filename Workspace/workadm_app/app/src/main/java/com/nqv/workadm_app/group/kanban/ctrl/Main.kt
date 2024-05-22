package com.nqv.workadm_app.group.kanban.ctrl

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data classes to represent boards, lists, and cards
data class Board(val id: Int, val name: String)
data class ListTask(val id: Int, val name: String, val cards: List<Card>)
data class Card(val id: Int, val name: String)

// Sample data
val sampleLists = listOf(
    ListTask(1, "To Do", listOf(Card(1, "Task 1"), Card(2, "Task 2"))),
    ListTask(2, "In Progress", listOf(Card(3, "Task 3"))),
    ListTask(3, "Done", listOf(Card(4, "Task 4"), Card(5, "Task 5")))
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TrelloApp(lists: List<ListTask>) {
    TrelloBoard(lists)
}

@Composable
fun TrelloBoard(lists: List<ListTask>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(lists.size) { index ->
            TrelloList(list = lists[index])
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TrelloList(list: ListTask) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = list.name, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            list.cards.forEach { card ->
                TrelloCard(card = card)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TrelloCard(card: Card) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = card.name)
        }
    }
}

@Preview
@Composable
fun PreviewApp() {
    TrelloApp(sampleLists)
}