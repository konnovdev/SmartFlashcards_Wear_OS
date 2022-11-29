package dev.konnov.smartflashcards.app.ui

import android.app.Activity
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Confirmation
import androidx.wear.compose.material.dialog.Dialog

@Composable
fun SuccessScreen() {
    var closeApp by remember { mutableStateOf(false) }
    if (closeApp) {
        closeApp()
    }

    Dialog(showDialog = true, onDismissRequest = {
        closeApp = true
    }) {
        Confirmation(
            onTimeout = {
                closeApp = true
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Review is finished",
                    modifier = Modifier.size(48.dp)
                )
            }
        ) {
            Text(
                text = "Cards reviewed",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun closeApp() {
    val activity = (LocalContext.current as? Activity) // temporary solution to close the app
    activity?.finish()
}

@Preview
@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun SuccessScreenPreview() {
    SuccessScreen()
}
