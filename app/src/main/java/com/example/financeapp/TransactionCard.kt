package com.example.financeapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.financeapp.domain.Transaction
import java.math.BigDecimal
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random

fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("MMM dd, hh:mm a")
    return dateFormat.format(this)
}

fun BigDecimal.toCurrency(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(this)
}

val categories = listOf(
    "Food" to Icons.Default.Fastfood,
    "Transport" to Icons.Default.DirectionsCar,
    "Shopping" to Icons.Default.ShoppingCart,
    "Health" to Icons.Default.LocalHospital,
    "Entertainment" to Icons.Default.Movie,
    "Utilities" to Icons.Default.Lightbulb
)

fun randomTransaction() = Transaction(
    category = categories.shuffled().first().first,
    value = BigDecimal.valueOf(Random().nextDouble() / Random().nextDouble())
)

@Composable
fun TransactionCard(uuid: String, value: BigDecimal, category: String, date: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        TransactionInfo(category, date, value)
    }
}

@Composable
private fun TransactionInfo(category: String, date: String, value: BigDecimal) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Filled.Restaurant, contentDescription = "")

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = category, style = MaterialTheme.typography.titleMedium)
                Text(text = date, style = MaterialTheme.typography.titleSmall)
            }

            Spacer(modifier = Modifier.width(2.dp))

            Text(text = value.toCurrency())

            Spacer(modifier = Modifier.width(16.dp))

            Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun Preview() {
    TransactionCard(
        uuid = "123", value = BigDecimal.valueOf(10), date = "jul. 02", category = "Restaurant"
    )
}