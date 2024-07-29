package pro.jayeshseth.commoncomponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

/**
 * Animated Common Scaffold for Home Screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(
    modifier: Modifier = Modifier,
    innerContentModifier: Modifier = Modifier,
    defaultColor: Color = MaterialTheme.colorScheme.surface,
    innerScrollState: ScrollState,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    title: @Composable () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val animatedColor = animateColorAsState(
        targetValue = if (topAppBarScrollBehavior.state.collapsedFraction > 0.5) {
            defaultColor.copy(0.5f)
        } else {
            defaultColor
        },
        animationSpec = tween(easing = LinearEasing),
        label = "animated top app bar color"
    )
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                scrollBehavior = topAppBarScrollBehavior,
                title = {
                    AnimatedVisibility(
                        visible = topAppBarScrollBehavior.state.collapsedFraction < 0.5,
                        enter = fadeIn(
                            animationSpec = tween(easing = LinearEasing)
                        ) + slideInVertically(animationSpec = tween(easing = LinearEasing)),
                        exit = fadeOut(
                            animationSpec = tween(easing = LinearEasing)
                        ) + slideOutVertically(animationSpec = tween(easing = LinearEasing))
                    ) {
                        title()
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = animatedColor.value,
                    scrolledContainerColor = animatedColor.value
                ),
            )
        }
    ) { innerPadding ->
        Box(
            modifier = innerContentModifier
                .padding(horizontal = 20.dp)
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                .verticalScroll(innerScrollState)
        ) { content(innerPadding) }
    }
}