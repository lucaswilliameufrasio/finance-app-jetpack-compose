package com.example.financeapp.data

import com.example.financeapp.domain.Transaction

object DummyRepository {
    private var _transactions = mutableListOf<Transaction>()
    val transactions get() = _transactions.toList()

    fun addTransaction(transaction: Transaction) {
        _transactions.add(transaction)
    }

    fun deleteTransaction(uuid: String) {
        _transactions.removeIf {
            uuid == it.uuid
        }
    }

    fun updateTransaction(transaction: Transaction) {
        deleteTransaction(transaction.uuid)
        _transactions.add(transaction)
    }

    fun clearTransactions() {
        _transactions.clear()
    }

    fun findTransaction(uuid: String): Transaction {
        return transactions.firstOrNull { it.uuid == uuid } ?: Transaction()
    }
}