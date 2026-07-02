package `in`.crypto.fake.coins

import `in`.crypto.core_model.Coin
import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Money
import `in`.crypto.fake.coins.FakeCoins.ada
import `in`.crypto.fake.coins.FakeCoins.btc
import `in`.crypto.fake.coins.FakeCoins.eth
import `in`.crypto.fake.coins.FakeCoins.sol
import `in`.crypto.fake.generator.PriceSimulator
import java.math.BigDecimal

object FakeCoins {

    val btc = Coin(
        id = "btc",
        icon = "₿",
        symbol = "BTC",
        currentPrice = Money(
            BigDecimal(102500),
            CurrencyCode.USD
        ),
        priceHistory =
            listOf(
                Money(BigDecimal(98000), CurrencyCode.USD),
                Money(BigDecimal(99500), CurrencyCode.USD),
                Money(BigDecimal(100200), CurrencyCode.USD),
                Money(BigDecimal(101000), CurrencyCode.USD),
                Money(BigDecimal(102500), CurrencyCode.USD)
            )
    )

    val eth = Coin(
        id = "eth",
        icon = "Ξ",
        symbol = "ETH",
        currentPrice = Money(
            BigDecimal(2350),
            CurrencyCode.USD
        ),
        priceHistory =
            listOf(
                Money(BigDecimal(2100), CurrencyCode.USD),
                Money(BigDecimal(2150), CurrencyCode.USD),
                Money(BigDecimal(2250), CurrencyCode.USD),
                Money(BigDecimal(2300), CurrencyCode.USD),
                Money(BigDecimal(2350), CurrencyCode.USD)
            )
    )

    val sol = Coin(
        id = "sol",
        icon = "S",
        symbol = "SOL",
        currentPrice = Money(
            BigDecimal(976),
            CurrencyCode.USD
        ),
        priceHistory =
            listOf(
                Money(BigDecimal(993), CurrencyCode.USD),
                Money(BigDecimal(995), CurrencyCode.USD),
                Money(BigDecimal(985), CurrencyCode.USD),
                Money(BigDecimal(974), CurrencyCode.USD),
                Money(BigDecimal(989), CurrencyCode.USD)
            )
    )

    val ada = Coin(
        id = "ada",
        icon = "Ā",
        symbol = "ADA",
        currentPrice = Money(
            BigDecimal(95),
            CurrencyCode.USD
        ),
        priceHistory =
            listOf(
                Money(BigDecimal(95), CurrencyCode.USD),
                Money(BigDecimal(106), CurrencyCode.USD),
                Money(BigDecimal(98), CurrencyCode.USD),
                Money(BigDecimal(104), CurrencyCode.USD),
                Money(BigDecimal(1010), CurrencyCode.USD)
            )
    )

}

fun getCoins() = listOf<Coin>(btc, eth, sol, ada)

fun getMarket(): Map<String, Coin> = getCoins().map {
    val currencyCode = it.currentPrice.currency
    val newPrice = PriceSimulator.nextPrice(it.currentPrice.amount)
    it.copy(currentPrice = Money(newPrice, currencyCode))
}.associateBy { it.id }

fun updateMarket(market: Map<String, Coin>): Map<String, Coin> {
    return market
        .mapValues {
            val coin = it.value
            coin.copy(
                currentPrice = Money(
                    amount = PriceSimulator
                        .nextPrice(coin.currentPrice.amount),
                    currency = coin.currentPrice.currency
                )
            )
        }
}

fun getCoinById(id: String): Coin {
    return when (id) {
        "btc" -> btc
        "eth" -> eth
        "sol" -> sol
        "ada" -> ada
        else -> btc
    }
}