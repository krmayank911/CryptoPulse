package `in`.crypto.core_model.helper

import `in`.crypto.core_model.Coin
import `in`.crypto.core_model.Money
import `in`.crypto.core_model.portfolio.Portfolio
import `in`.crypto.core_model.portfolio.PortfolioMetrics
import java.math.BigDecimal
import java.math.RoundingMode

object PortfolioCalculator {

    fun calculate(
        portfolio: Portfolio,
        market: Map<String, Coin>
    ): PortfolioMetrics {

        var investedAmount = Money(BigDecimal.ZERO, portfolio.currencyCode)
        var currentValue = Money(BigDecimal.ZERO, portfolio.currencyCode)

        portfolio.holdings.forEach { holding ->

            val coin = market[holding.coinId]
            if (coin != null) {
                investedAmount += holding.averageBuyPrice * holding.quantity
                currentValue += coin.currentPrice * holding.quantity
            }

        }

        val pnl = currentValue - investedAmount
        val pnlPercentage = if (investedAmount == BigDecimal.ZERO) {
            0f
        } else {
            (pnl.amount / investedAmount.amount.setScale(2, RoundingMode.HALF_UP)).toFloat() * 100
        }

        return PortfolioMetrics(
            investedAmount = investedAmount,
            currentValue = currentValue,
            pnl = pnl,
            pnlPercentage = pnlPercentage
        )
    }

}