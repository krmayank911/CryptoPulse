package `in`.crypto.shared_ui.mappers

import `in`.crypto.core_model.Transaction
import `in`.crypto.core_model.TransactionType
import `in`.crypto.shared_ui.models.TransactionListItemUiModel

fun Transaction.toTransactionListItemUiModel(): TransactionListItemUiModel {

    val title = when (transactionType) {
        TransactionType.BUY -> "Bought ${coinId.uppercase()}"
        TransactionType.SELL -> "Sold ${coinId.uppercase()}"
    }

    return TransactionListItemUiModel(
        id = id,
        title = title,
        timestamp = occurredAt,
        amount = pricePerUnit.getValue(),
    )
}