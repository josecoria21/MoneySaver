package dev.propoc.moneysaver.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class FabAction(
    val icon: @Composable () -> Unit,
    val label: String,
    val onClick: () -> Unit
)

@Composable
fun MultiActionFab(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    actions: List<FabAction>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        actions.forEach { action ->
            AnimatedVisibility(visible = expanded) {
                FloatingActionButton(onClick = {
                    action.onClick()
                    onExpandedChange(false)
                }) {
                    action.icon()
                }
            }
        }
        FloatingActionButton(onClick = { onExpandedChange(!expanded) }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = if (expanded) "Close" else "Open"
            )
        }
    }
}
