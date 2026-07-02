package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import java.math.BigDecimal

@Immutable
data class Holding(
    val coinId: String,
    val quantity: BigDecimal,
    val averageBuyPrice: Money,
)