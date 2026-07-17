package `in`.crypto.feature_dashboard.source_fake

import `in`.crypto.core_model.coin.Coin
import `in`.crypto.core_model.Holding
import `in`.crypto.core_model.Transaction
import `in`.crypto.core_model.coin.CoinId
import `in`.crypto.core_model.market.PortfolioData
import `in`.crypto.fake.coins.getCoins
import `in`.crypto.fake.coins.updateCoins
import `in`.crypto.fake.holdings.getMyHoldings
import `in`.crypto.fake.transactions.getMyTransactions
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class FakeDashboardFeed: DashboardFeed {

    private var coins: PersistentMap<CoinId, Coin> = persistentMapOf()
    private var holdings: PersistentList<Holding> = persistentListOf()
    private var transactions: PersistentList<Transaction> = persistentListOf()

    init {
        coins = getCoins().associateBy { it.id }.toPersistentMap()
        holdings = getMyHoldings().toPersistentList()
        transactions = getMyTransactions().toPersistentList()
    }


    override fun observe(): Flow<PortfolioData> {
        return flow {

            emit(
                PortfolioData(
                    coins = coins,
                    holdings = holdings,
                    transactions = transactions
                )
            )

            while (currentCoroutineContext().isActive) {
                coins = updateCoins(coins)
                emit(
                    PortfolioData(
                        coins = coins,
                        holdings = holdings,
                        transactions = transactions
                    )
                )
                delay(1000)
            }
        }
    }
}

