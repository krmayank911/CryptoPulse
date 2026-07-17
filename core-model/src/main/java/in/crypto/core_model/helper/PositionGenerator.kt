package `in`.crypto.core_model.helper

import `in`.crypto.core_model.coin.Coin
import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Holding
import `in`.crypto.core_model.Money
import `in`.crypto.core_model.Position

object PositionGenerator {

    fun buildPosition(
        holding: Holding,
        coin: Coin
    ): Position {

        val currentValue = holding.quantity * coin.currentPrice.amount
        val invested = holding.quantity * holding.averageBuyPrice.amount
        val pnl = currentValue - invested
        val pnlPercentage = (pnl / invested).toFloat() * 100

        return Position(
            coin = coin,
            holding = holding,
            investedAmount = Money(invested, CurrencyCode.USD),
            currentValue = Money(currentValue, CurrencyCode.USD),
            pnl = Money(pnl, CurrencyCode.USD),
            pnlPercentage = pnlPercentage
        )
    }
}
