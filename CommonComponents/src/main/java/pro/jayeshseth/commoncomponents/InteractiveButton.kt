package pro.jayeshseth.commoncomponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * An Interactive Button with `Hover` and `Pressed` interaction
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InteractiveButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    longPressDelay: Duration = 100.milliseconds,
    onLongPress: () -> Unit = {},
    height: Dp = 100.dp,
    padding: PaddingValues = PaddingValues(12.dp),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val scope = rememberCoroutineScope()
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val buttonInteracted = isPressed.or(isHovered)

    val animateColor by animateColorAsState(
        targetValue = if (buttonInteracted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
        animationSpec = tween(500),
        label = "animated button color",
    )
    val animateTextColor by animateColorAsState(
        targetValue = if (buttonInteracted) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary,
        animationSpec = tween(500),
        label = "animated button color",
    )
    val buttonDp by animateDpAsState(
        targetValue = if (buttonInteracted) 5.dp else 100.dp,
        animationSpec = tween(500),
        label = "animate button shape",
    )
    Surface(
        shape = RoundedCornerShape(size = buttonDp),
        color = animateColor,
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .padding(padding)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = onClick,
            )
            .pointerInput(onLongPress) {
                awaitEachGesture {
                    val initialDown = awaitFirstDown(requireUnconsumed = false)
                    val pressScope = scope.launch {
                        while (initialDown.pressed) {
                            onLongPress()
                            delay(longPressDelay)
                        }
                    }
                    waitForUpOrCancellation()
                    pressScope.cancel()
                }
            },
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(12.dp)) {
            Text(
                text = text,
                color = animateTextColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = textModifier,
            )
        }
    }
}

/**
 * An Interactive Button with `Hover` and `Pressed` interaction for long click action
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InteractiveButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onLongClick: () -> Unit = {},
    height: Dp = 100.dp,
    padding: PaddingValues = PaddingValues(12.dp),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val buttonInteracted = isPressed.or(isHovered)

    val animateColor by animateColorAsState(
        targetValue = if (buttonInteracted) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary,
        animationSpec = tween(500),
        label = "animated button color",
    )
    val animateTextColor by animateColorAsState(
        targetValue = if (buttonInteracted) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onPrimary,
        animationSpec = tween(500),
        label = "animated button color",
    )
    val buttonDp by animateDpAsState(
        targetValue = if (buttonInteracted) 5.dp else 100.dp,
        animationSpec = tween(500),
        label = "animate button shape",
    )
    Surface(
        shape = RoundedCornerShape(size = buttonDp),
        color = animateColor,
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .padding(padding)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = onClick,
                onLongClick = onLongClick
            )
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(12.dp)) {
            Text(
                text = text,
                color = animateTextColor,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}