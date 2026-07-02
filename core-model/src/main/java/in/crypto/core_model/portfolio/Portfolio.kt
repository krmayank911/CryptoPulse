package `in`.crypto.core_model.portfolio

import androidx.compose.runtime.Immutable
import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Holding
import `in`.crypto.core_model.Transaction

@Immutable
data class Portfolio(
    val id: String,
    val holdings: List<Holding>,
    val transactions: List<Transaction>,
    val currencyCode: CurrencyCode,
)
