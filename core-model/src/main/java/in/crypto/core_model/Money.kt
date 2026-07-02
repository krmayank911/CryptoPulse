package `in`.crypto.core_model

import androidx.compose.runtime.Immutable
import java.math.BigDecimal

@Immutable
data class Money(
    val amount: BigDecimal,
    val currency: CurrencyCode
) {

    operator fun plus(other: Money): Money {
        require(currency == other.currency)
        return copy(amount = amount + other.amount)
    }

    operator fun minus(other: Money) : Money {
        require(currency == other.currency)
        return copy(amount = amount - other.amount)
    }

    operator fun times(multiplier: BigDecimal): Money {
        return copy(amount = amount * multiplier)
    }

    fun getValue(): String {
        return "${currency.symbol}${getFormattedAmount()}"
    }

    fun getFormattedAmount(): String {
        return if (amount < BigDecimal.ZERO) String.format("%.2f", amount.multiply(-BigDecimal.ONE))
        else String.format("%.2f", amount)
    }
}

sealed class CurrencyCode(val symbol: String) {
    object USD: CurrencyCode("$")
    object INR: CurrencyCode("₹")
    object EUR: CurrencyCode("€")
}
