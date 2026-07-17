package `in`.crypto.core_model.market

import `in`.crypto.core_model.Holding
import `in`.crypto.core_model.Transaction
import `in`.crypto.core_model.coin.Coin
import `in`.crypto.core_model.coin.CoinId
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap

data class PortfolioData(
    val coins: PersistentMap<CoinId, Coin>,
    val holdings: PersistentList<Holding>,
    val transactions: PersistentList<Transaction>
)
