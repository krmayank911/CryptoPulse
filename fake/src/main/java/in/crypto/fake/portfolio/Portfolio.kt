package `in`.crypto.fake.portfolio

import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.portfolio.Portfolio
import `in`.crypto.fake.holdings.getMyHoldings
import `in`.crypto.fake.transactions.getMyTransactions

fun getMyPortfolio(): Portfolio = Portfolio(
    id = "1",
    holdings = getMyHoldings(),
    transactions = getMyTransactions(),
    currencyCode = CurrencyCode.USD
)