package `in`.crypto.fake.coins

import `in`.crypto.core_model.coin.Coin
import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Money
import `in`.crypto.core_model.coin.CoinId
import `in`.crypto.fake.coins.FakeCoins.ada
import `in`.crypto.fake.coins.FakeCoins.btc
import `in`.crypto.fake.coins.FakeCoins.eth
import `in`.crypto.fake.coins.FakeCoins.sol
import `in`.crypto.fake.generator.PriceSimulator
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import java.math.BigDecimal
import kotlin.random.Random

object FakeCoins {

    val btc = Coin(
        id = CoinId("btc"),
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
            ).toPersistentList()
    )

    val eth = Coin(
        id = CoinId("eth"),
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
            ).toPersistentList()
    )

    val sol = Coin(
        id = CoinId("sol"),
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
            ).toPersistentList()
    )

    val ada = Coin(
        id = CoinId("ada"),
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
            ).toPersistentList()
    )

}

fun getCoins() = listOf<Coin>(btc, eth, sol, ada)

fun getMarket(): Map<String, Coin> = getCoins().map {
    val currencyCode = it.currentPrice.currency
    val newPrice = PriceSimulator.nextPrice(it.currentPrice.amount)
    it.copy(currentPrice = Money(newPrice, currencyCode))
}.associateBy { it.id.value }

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

fun updateCoins(
    coins: PersistentMap<CoinId, Coin>
): PersistentMap<CoinId, Coin> {

    val btcId = randomCoinId(coins)
    val btc = coins[btcId] ?: return coins

    val newValue = PriceSimulator.nextPrice(btc.currentPrice.amount)

    val newPrice = btc.currentPrice.copy(amount = newValue)

    val updatedHistory = (btc.priceHistory + newPrice)
        .takeLast(10)
        .toPersistentList()

    val updatedBtc = btc.copy(
        currentPrice = newPrice,
        priceHistory = updatedHistory
    )

    return coins.put(btcId, updatedBtc)

    /*return coins.mapValues {
        val coin = it.value
        if (coin.id.value == "btc") {
            btc.copy(
                currentPrice = Money(
                    amount = PriceSimulator.nextPrice(btc.currentPrice.amount),
                    currency = btc.currentPrice.currency
                )
            )
        } else {
            coin
        }
    }.toPersistentMap()*/
}

private val random = Random.Default

private fun randomCoinId(
    coins: PersistentMap<CoinId, Coin>
): CoinId = coins.keys.random(random)

fun getCoinById(id: String): Coin {
    return when (id) {
        "btc" -> btc
        "eth" -> eth
        "sol" -> sol
        "ada" -> ada
        else -> btc
    }
}