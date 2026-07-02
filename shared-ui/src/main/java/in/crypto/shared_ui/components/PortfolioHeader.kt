package `in`.crypto.shared_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.crypto.core_designsystem.components.CPBodyText
import `in`.crypto.core_designsystem.components.CPCaptionText
import `in`.crypto.core_designsystem.components.CPDataText
import `in`.crypto.core_designsystem.components.CPDisplayText
import `in`.crypto.core_designsystem.components.CPLabelText
import `in`.crypto.core_designsystem.tokens.CPTheme
import `in`.crypto.core_designsystem.tokens.CPThemeDark
import `in`.crypto.fake.coins.getMarket
import `in`.crypto.fake.portfolio.getMyPortfolio
import `in`.crypto.shared_ui.mappers.toPortfolioUiModel
import `in`.crypto.shared_ui.models.CryptoState
import `in`.crypto.shared_ui.models.PortfolioUiModel

@Composable
fun PortfolioHeader(
    modifier: Modifier = Modifier,
    portfolioUiModel: PortfolioUiModel,
    theme: CPTheme = CPThemeDark
) {

    val accentTextColor = when (portfolioUiModel.cryptoState) {
        CryptoState.PROFIT -> theme.gainColor
        CryptoState.LOSS -> theme.lossColor
        else -> theme.accentColor
    }

    Column(
        modifier = modifier
    ) {
        val value = portfolioUiModel.value
        val changeAmount = portfolioUiModel.changeAmount
        val changePercentage = "${portfolioUiModel.changePercentage} today"

        CPCaptionText(
            text = "Portfolio Value",
            color = theme.textColorSecondary
        )
        CPDisplayText(
            text = value,
            color = theme.textColorContrast,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CPDataText(
                text = changeAmount,
                color = accentTextColor
            )
            CPBodyText(
                text = changePercentage,
                color = theme.textColorSecondary
            )
        }
        Spacer(modifier = Modifier.size(8.dp))

        val shape = RoundedCornerShape(12.dp)

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = theme.borderColorDefault,
                    shape = shape
                )
                .background(
                    color = theme.elevatedBackground,
                    shape = shape
                )
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = theme.gainColor,
                        shape = RoundedCornerShape(16.dp)
                    )
            )
            CPLabelText(
                text = "Live updates every 250ms",
                color = theme.textColorSecondary
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                CPBodyText(
                    text = "Market Open",
                    color = theme.gainColor
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
fun PortfolioHeaderPreview() {

    PortfolioHeader(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, 16.dp),
        portfolioUiModel = getMyPortfolio().toPortfolioUiModel(getMarket()),
        theme = CPThemeDark
    )

}

