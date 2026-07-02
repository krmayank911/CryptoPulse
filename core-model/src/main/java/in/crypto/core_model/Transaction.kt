package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import java.math.BigDecimal

@Immutable
data class Transaction(
    val id: String,
    val coinId: String,
    val transactionType: TransactionType,
    val quantity: BigDecimal,
    val pricePerUnit: Money,
    val occurredAt: Long
)

enum class TransactionType { BUY, SELL }
