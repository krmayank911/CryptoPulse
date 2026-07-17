package `in`.crypto.fake.holdings

import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Holding
import `in`.crypto.core_model.Money
import `in`.crypto.core_model.coin.CoinId
import `in`.crypto.fake.holdings.FakeHoldings.btcHolding
import `in`.crypto.fake.holdings.FakeHoldings.ethHolding
import java.math.BigDecimal

object FakeHoldings {
    val btcHolding = Holding(
        coinId = CoinId("btc"),
        quantity = BigDecimal(0.75),
        averageBuyPrice = Money(
            BigDecimal(94000),
            CurrencyCode.USD
        )
    )
    val ethHolding = Holding(
        coinId = CoinId("eth"),
        quantity = BigDecimal(2.5),
        averageBuyPrice = Money(
            BigDecimal(2200),
            CurrencyCode.USD
        )
    )
}

fun getMyHoldings(): List<Holding> {
    return listOf(btcHolding, ethHolding)
}