package `in`.crypto.shared_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.crypto.core_designsystem.components.CPCaptionText
import `in`.crypto.core_designsystem.components.CPDataText
import `in`.crypto.core_designsystem.components.CPHeadlineText
import `in`.crypto.core_designsystem.components.CPSymbolText
import `in`.crypto.core_designsystem.tokens.CPTheme
import `in`.crypto.core_designsystem.tokens.CPThemeDark
import `in`.crypto.core_ui.components.LogCompositions
import `in`.crypto.fake.transactions.transaction1
import `in`.crypto.shared_ui.mappers.toTransactionListItemUiModel
import `in`.crypto.shared_ui.models.TransactionListItemUiModel
import `in`.crypto.shared_ui.models.getTimestampFormatted

@Composable
fun TransactionListItem(
    modifier: Modifier = Modifier,
    transaction: TransactionListItemUiModel,
    theme: CPTheme = CPThemeDark
) {

    val accentBackgroundColor = theme.borderColorAccent
    val accentTextColor = theme.accentColor

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if (transaction.icon == null) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = accentBackgroundColor,
                        shape = RoundedCornerShape(100.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CPSymbolText(
                    text = "A",
                    color = accentTextColor,
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            val date = transaction.getTimestampFormatted()
            val headlineText = transaction.title.orEmpty()
            CPHeadlineText(headlineText)
            CPCaptionText(
                text = date,
                color = theme.textColorSecondary
            )
        }
        LogCompositions("Transaction ${transaction.id} ")
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            val amount = transaction.amount.orEmpty()
            CPDataText(amount)
        }
    }

}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun TransactionListItemPreview() {
    val theme = CPThemeDark
    TransactionListItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        transaction = transaction1.toTransactionListItemUiModel(),
        theme = theme
    )
}