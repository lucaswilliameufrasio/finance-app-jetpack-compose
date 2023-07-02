package com.example.financeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.ClearAll
import androidx.compose.material.icons.filled.MoveUp
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financeapp.data.randomTransaction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Overview(transactionViewModel: TransactionsViewModel = viewModel()) {
    val uiState by transactionViewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 7
        }
    }

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
                ),
                state = listState
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

        if (showScrollToTopButton) {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.shapes.extraLarge
                        ),
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowUpward,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}
