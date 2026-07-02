package `in`.crypto.shared_ui.models

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

data class TransactionListItemUiModel(
    val id: String = "",
    val icon: String? = null,
    val title: String? = null,
    val timestamp: Long? = null,
    val amount: String? = null,
    val changeText: String? = null,
    val state: CryptoState = CryptoState.NEUTRAL
)


fun TransactionListItemUiModel.getTimestampFormatted(): String {

    val instant = Instant.ofEpochMilli(timestamp!!)

    // 2. Define your desired pattern and timezone
    val formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mm a", Locale.getDefault())
        .withZone(ZoneId.systemDefault()) // Or ZoneId.of("IST")

    // 3. Format the instant into a string
    return formatter.format(instant)
}