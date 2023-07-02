package com.example.financeapp

import androidx.lifecycle.ViewModel
import com.example.financeapp.data.DummyRepository
import com.example.financeapp.domain.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

class TransactionsViewModel(private val repository: DummyRepository = DummyRepository) :
    ViewModel() {
    private val filter = MutableStateFlow<String?>(null)
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        _uiState.value = UiState(transactions = repository.transactions)
    }

    fun add(transaction: Transaction) {
        repository.addTransaction(transaction)
        updateUiState()
    }

    fun clear() {
        repository.clearTransactions()
        updateUiState()
    }

    private fun updateUiState() {
        val transactionsList = repository.transactions
        val transactions = if (filter.value != null) {
            transactionsList.filter { it.category == filter.value }
        } else {
            transactionsList
        }
        _uiState.value = UiState(
            transactions = transactions,
            total = transactionsList.sumOf { it.value })
    }

    data class UiState(
        val transactions: List<Transaction> = emptyList(),
        val total: BigDecimal = transactions.sumOf { it.value }
    )
}