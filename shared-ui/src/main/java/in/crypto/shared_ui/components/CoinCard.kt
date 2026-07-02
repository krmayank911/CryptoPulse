package `in`.crypto.shared_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.crypto.core_designsystem.components.CPDataText
import `in`.crypto.core_designsystem.components.CPLabelText
import `in`.crypto.core_designsystem.components.CPSymbolText
import `in`.crypto.core_designsystem.theme.Radius
import `in`.crypto.core_designsystem.tokens.CPTheme
import `in`.crypto.core_designsystem.tokens.CPThemeDark
import `in`.crypto.core_model.helper.PositionGenerator.buildPosition
import `in`.crypto.fake.coins.getCoinById
import `in`.crypto.fake.holdings.FakeHoldings
import `in`.crypto.shared_ui.mappers.toCoinCardUiModel
import `in`.crypto.shared_ui.models.CryptoState
import `in`.crypto.shared_ui.models.CoinCardUiModel

@Composable
fun CoinCard(
    modifier: Modifier = Modifier,
    cardUiModel: CoinCardUiModel,
    theme: CPTheme = CPThemeDark,
    accentTextColor: Color,
    accentBackgroundColor: Color
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentSize(align = Alignment.CenterStart)
            ) {

                val symbol = cardUiModel.symbol.orEmpty()
                val currentPrice = cardUiModel.currentPrice.orEmpty()
                CPLabelText(
                    text = symbol,
                    color = theme.textColorSecondary
                )
                Spacer(modifier = Modifier.size(4.dp))
                CPDataText(
                    text = currentPrice,
                    color = theme.textColorContrast
                )
            }
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
                    text = cardUiModel.icon ?: "A",
                    color = accentTextColor,
                )
            }
        }

        Spacer(modifier = Modifier.size(4.dp))
        // chart
        Box(
            Modifier.height(36.dp)
                .fillMaxWidth()
                .background(
                    color = accentBackgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
        )
        // space
        Spacer(modifier = Modifier.size(4.dp))
        // status
        CPDataText(
            text = cardUiModel.changeText.orEmpty(),
            color = accentTextColor
        )
    }
}

@Composable
@Preview
fun CoinCardPreview(
    theme: CPTheme = CPThemeDark
) {

    val holding = FakeHoldings.btcHolding
    val cardUiModel: CoinCardUiModel = buildPosition(holding, getCoinById(holding.coinId)).toCoinCardUiModel(true)

    val (accentTextColor, accentBackgroundColor, accentBorderColor) = when (cardUiModel.state) {
        CryptoState.PROFIT -> Triple(theme.gainColor, theme.gainBackground, theme.borderColorGain)
        CryptoState.LOSS -> Triple(theme.lossColor, theme.lossBackground, theme.borderColorLoss)
        else -> Triple(theme.textColorPrimary, theme.textColorPrimary, theme.borderColorDefault)
    }

    CoinCard(
        modifier = Modifier
            .padding(16.dp)
            .width(180.dp)
            .wrapContentHeight(align = Alignment.CenterVertically)
            .background(
                color = theme.cardBackground,
                shape = RoundedCornerShape(Radius().medium)
            )
            .border(
                width = 0.5.dp,
                color = accentBorderColor,
                shape = RoundedCornerShape(Radius().medium)
            )
            .padding(14.dp),
        cardUiModel = cardUiModel,
        theme = theme,
        accentTextColor,
        accentBackgroundColor
    )
}