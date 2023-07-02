package com.example.financeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financeapp.data.randomTransaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Overview(transactionViewModel: TransactionsViewModel = viewModel()) {
    val uiState by transactionViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
                colors = TopAppBarDefaults.largeTopAppBarColors(MaterialTheme.colorScheme.background),
                title = {
                    Text(
                        text = "Welcome back, \nLucas Eufrasio",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ClearAll,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                transactionViewModel.add(randomTransaction())
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add transaction")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = "Transactions",
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            )
            LazyColumn(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp
                ),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 16.dp
                )
            ) {
                items(uiState.transactions.size) { index ->
                    TransactionCard(
                        uuid = uiState.transactions[index].uuid,
                        value = uiState.transactions[index].value,
                        category = uiState.transactions[index].category,
                        date = uiState.transactions[index].date.formatDate()
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}