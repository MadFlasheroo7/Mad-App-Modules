package pro.jayeshseth.commoncomponents

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Column with ability to modify status bar color
 */
@Composable
fun StatusBarAwareThemedColumn(
    modifier: Modifier = Modifier,
    statusBarColor: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
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
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}

/**
 * Column with ability to modify navigation bar color
 */
@Composable
fun NavigationBarAwareThemedColumn(
    modifier: Modifier = Modifier,
    navigationBarColor: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
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
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}

/**
 * Column with ability to modify system bar color
 */
@Composable
fun SystemBarAwareThemedColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    systemBarsColors: Color = MaterialTheme.colorScheme.surface.copy(0.5f),
    content: @Composable ColumnScope.() -> Unit
) {
    val useDarkIcons = !isSystemInDarkTheme()
    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            systemBarsColors,
            useDarkIcons
        )
        onDispose { }
    }
    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) { content() }
}