package com.rezyfr.foodmarket.core.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rezyfr.foodmarket.core.ui.component.snackbar.FMSnacbarkState
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun rememberSnackBarState(): FMSnacbarkState {
    return remember { FMSnacbarkState() }
}
@Composable
fun FMSnackBar(
    modifier: Modifier = Modifier,
    state: FMSnacbarkState,
    duration: Long = 3000L,
    icon: ImageVector = Icons.Filled.Close,
    containerColor: Color = Color.White,
    contentColor: Color = MaterialTheme.colors.onError,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(delayMillis = 300),
        expandFrom = Alignment.Top
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(delayMillis = 300),
        shrinkTowards = Alignment.Top
    ),
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        FMSnackbarComponent(
            state,
            duration,
            containerColor,
            contentColor,
            verticalPadding,
            horizontalPadding,
            icon,
            enterAnimation,
            exitAnimation
        )
    }
}
@Composable
internal fun FMSnackbarComponent(
    state: FMSnacbarkState,
    duration: Long,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: ImageVector,
    enterAnimation: EnterTransition,
    exitAnimation: ExitTransition,
) {
    var showSnackbar by remember { mutableStateOf(false) }
    val message by rememberUpdatedState(newValue = state.message.value)

    DisposableEffect(
        key1 = state.updateState
    ) {
        showSnackbar = true
        val timer = Timer("Animation Timer", true)
        timer.schedule(duration) {
            showSnackbar = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = state.isNotEmpty() && showSnackbar,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            Snackbar(
                message,
                containerColor,
                contentColor,
                verticalPadding,
                horizontalPadding,
                icon
            )
        }
    }
}
@Composable
internal fun Snackbar(
    message: String?,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: ImageVector,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .background(
                color = containerColor,
                shape = RectangleShape
            )
            .padding(vertical = verticalPadding)
            .padding(horizontal = horizontalPadding)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(4f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = message ?: "Unknown",
                color = contentColor,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor
            )
        }
    }
}