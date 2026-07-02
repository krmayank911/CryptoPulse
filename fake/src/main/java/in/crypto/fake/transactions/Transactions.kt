package `in`.crypto.fake.transactions

import `in`.crypto.core_model.CurrencyCode
import `in`.crypto.core_model.Money
import `in`.crypto.core_model.Transaction
import `in`.crypto.core_model.TransactionType
import java.math.BigDecimal

val transaction1 = Transaction(
        id = "tx1",
        coinId = "btc",
        transactionType = TransactionType.BUY,
        quantity = BigDecimal(0.5),
        pricePerUnit = Money(BigDecimal(90000), CurrencyCode.USD),
        occurredAt = System.currentTimeMillis() - (36 * 3600000)
    )

val transaction2 = Transaction(
        id = "tx2",
        coinId = "btc",
        transactionType = TransactionType.BUY,
        quantity = BigDecimal(0.25),
        pricePerUnit = Money(BigDecimal(98000), CurrencyCode.USD),
        occurredAt = System.currentTimeMillis() - (24 * 3600000)
    )

val transaction3 = Transaction(
        id = "tx3",
        coinId = "eth",
        transactionType = TransactionType.BUY,
        quantity = BigDecimal(2.5),
        pricePerUnit = Money(BigDecimal(2200), CurrencyCode.USD),
        occurredAt = System.currentTimeMillis() - (18 * 3600000)
    )

val transactions = listOf(transaction1, transaction2, transaction3)

fun getMyTransactions() = transactions