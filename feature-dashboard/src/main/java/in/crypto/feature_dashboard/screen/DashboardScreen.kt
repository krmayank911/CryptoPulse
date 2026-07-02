package `in`.crypto.feature_dashboard.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import `in`.crypto.core_designsystem.components.CPTitleText
import `in`.crypto.core_designsystem.theme.Radius
import `in`.crypto.core_designsystem.tokens.CPThemeDark
import `in`.crypto.feature_dashboard.actions.DashboardAction
import `in`.crypto.feature_dashboard.presentation.DashboardViewModel
import `in`.crypto.feature_dashboard.`source-fake`.FakeDashboardFeed
import `in`.crypto.feature_dashboard.state.DashboardUiState
import `in`.crypto.shared_ui.components.CoinCard
import `in`.crypto.shared_ui.components.PortfolioHeader
import `in`.crypto.shared_ui.components.TransactionListItem
import `in`.crypto.shared_ui.models.CryptoState



@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    dashboardUiState: DashboardUiState,
    theme: CPThemeDark = CPThemeDark,
    actions: (DashboardAction) -> Unit = {},
) {

    Column(
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))

        val portfolioUiModel = dashboardUiState.portfolioUiModel
        if (portfolioUiModel != null) {
            PortfolioHeader(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                portfolioUiModel = portfolioUiModel,
                theme = CPThemeDark
            )
        }

        val holdings = dashboardUiState.holdings
        if (holdings.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            CPTitleText(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Your Assets",
                color = theme.textColorSecondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
                horizontalArrangement = Arrangement.Absolute.SpaceAround
            ) {
                items(holdings) { model ->
                    val (accentTextColor, accentBackgroundColor, accentBorderColor) = when (model.state) {
                        CryptoState.PROFIT -> Triple(theme.gainColor, theme.gainBackground, theme.borderColorGain)
                        CryptoState.LOSS -> Triple(theme.lossColor, theme.lossBackground, theme.borderColorLoss)
                        else -> Triple(theme.textColorPrimary, theme.textColorPrimary, theme.borderColorDefault)
                    }
                    Box(modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(
                            color = theme.cardBackground,
                            shape = RoundedCornerShape(Radius().medium)
                        )
                        .border(
                            width = 0.5.dp,
                            color = accentBorderColor,
                            shape = RoundedCornerShape(Radius().medium)
                        )
                        .clickable {
                            actions(DashboardAction.CoinClick(model.id))
                        }
                    ) {
                        CoinCard(
                            modifier = Modifier
                                .width(176.dp)
                                .padding(12.dp),
                            cardUiModel = model,
                            theme = CPThemeDark,
                            accentTextColor = accentTextColor,
                            accentBackgroundColor = accentBackgroundColor
                        )
                    }
                }
            }
        }

        val transactions = dashboardUiState.transactions
        if (transactions.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            CPTitleText(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Transactions",
                color = theme.textColorSecondary
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(transactions) { model ->
                    TransactionListItem(
                        modifier = Modifier.padding(top = 16.dp)
                            .clickable {
                                actions(DashboardAction.TransactionClick(model.id))
                            },
                        transaction = model,
                        theme = theme
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFF0A0D14)
@Composable
fun DashboardScreenPreview() {

    val viewModel: DashboardViewModel = DashboardViewModel(FakeDashboardFeed())
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    DashboardScreen(
        modifier = Modifier.fillMaxSize(),
        dashboardUiState = state,
        theme = CPThemeDark,
        actions = {}
    )
}