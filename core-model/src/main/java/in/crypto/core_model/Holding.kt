package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import `in`.crypto.core_model.coin.CoinId
import java.math.BigDecimal

@Immutable
data class Holding(
    val coinId: CoinId,
    val quantity: BigDecimal,
    val averageBuyPrice: Money,
)