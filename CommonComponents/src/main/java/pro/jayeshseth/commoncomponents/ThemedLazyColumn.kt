package pro.jayeshseth.commoncomponents

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * LazyColumn with ability to modify status bar color
 */
@Composable
fun StatusBarAwareThemedLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    statusBarColor: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit
) {
    val useDarkIcons = !isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(
            statusBarColor,
            useDarkIcons
        )
        onDispose { }
    }
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}

/**
 * LazyColumn with ability to modify navigation bar color
 */
@Composable
fun NavigationBarAwareThemedLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    navigationBarColor: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit
) {
    val useDarkIcons = !isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setNavigationBarColor(
            navigationBarColor,
            useDarkIcons
        )
        onDispose { }
    }
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}

/**
 * LazyColumn with ability to modify system bars color
 */
@Composable
fun SystemBarAwareThemedLazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    systemBarColor: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: LazyListScope.() -> Unit
) {
    val useDarkIcons = !isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            systemBarColor,
            useDarkIcons
        )
        onDispose { }
    }
    LazyColumn(
        modifier = modifier,
        state = state,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}