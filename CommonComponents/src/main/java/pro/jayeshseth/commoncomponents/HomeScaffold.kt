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
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

/**
 * Animated Common Scaffold for Home Screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(
    title: @Composable () -> Unit,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    verticalScrollState: ScrollState? = null,
    innerContentModifier: Modifier = Modifier,
    homeScaffoldColors: HomeScaffoldColors = HomeScaffoldDefaults.homeScaffoldColors(),
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable (RowScope.() -> Unit) = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val containerColor = homeScaffoldColors.containerColor(topAppBarScrollBehavior).value
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedVisibility(
                        visible = topAppBarScrollBehavior.state.collapsedFraction < 0.5,
                        enter = fadeIn(
                            animationSpec = tween(easing = LinearEasing)
                        ) + slideInVertically(animationSpec = tween(easing = LinearEasing)),
                        exit = fadeOut(
                            animationSpec = tween(easing = LinearEasing)
                        ) + slideOutVertically(animationSpec = tween(easing = LinearEasing))
                    ) { title() }
                },
                scrollBehavior = topAppBarScrollBehavior,
                navigationIcon = navigationIcon,
                actions = actions,
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = containerColor,
                    scrolledContainerColor = containerColor,
                    navigationIconContentColor = homeScaffoldColors.navigationIconContentColor,
                    titleContentColor = homeScaffoldColors.titleContentColor,
                    actionIconContentColor = homeScaffoldColors.actionIconContentColor
                ),
            )
        }
    ) { innerPadding ->
        Box(
            modifier = innerContentModifier
                .padding(HomeScaffoldDefaults.innerContentPadding)
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                .then(
                    if (verticalScrollState != null) {
                        Modifier.verticalScroll(verticalScrollState)
                    } else {
                        Modifier
                    }
                )
        ) { content(innerPadding) }
    }
}

/**
 * Default values for [HomeScaffold]
 */
object HomeScaffoldDefaults {

    val innerContentPadding = PaddingValues(horizontal = 20.dp)

    @Composable
    fun homeScaffoldColors(
        containerColor: Color = MaterialTheme.colorScheme.surface,
        navigationIconContentColor: Color = Color.Unspecified,
        titleContentColor: Color = Color.Unspecified,
        actionIconContentColor: Color = Color.Unspecified,
    ) = HomeScaffoldColors(
        containerColor = containerColor,
        navigationIconContentColor = navigationIconContentColor,
        titleContentColor = titleContentColor,
        actionIconContentColor = actionIconContentColor
    )
}

@Immutable
class HomeScaffoldColors internal constructor(
    private val containerColor: Color,
    val navigationIconContentColor: Color,
    val titleContentColor: Color,
    val actionIconContentColor: Color,
) {

    /**
     * Default container color
     */
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun containerColor(topAppBarScrollBehavior: TopAppBarScrollBehavior): State<Color> {
        val animatedColor = animateColorAsState(
            targetValue = if (topAppBarScrollBehavior.state.collapsedFraction > 0.5) {
                containerColor.copy(0.5f)
            } else {
                containerColor
            },
            animationSpec = tween(easing = LinearEasing),
            label = "animated container color"
        )
        return rememberUpdatedState(animatedColor.value)
    }

    /**
     * Returns a copy of [HomeScaffoldColors], optionally overriding some of the values.
     */
    fun copy(
        containerColor: Color = this.containerColor,
        navigationIconContentColor: Color = this.navigationIconContentColor,
        titleContentColor: Color = this.titleContentColor,
        actionIconContentColor: Color = this.actionIconContentColor,
    ) = HomeScaffoldColors(
        containerColor.takeOrElse { this.containerColor },
        navigationIconContentColor.takeOrElse { this.navigationIconContentColor },
        titleContentColor.takeOrElse { this.titleContentColor },
        actionIconContentColor.takeOrElse { this.actionIconContentColor }
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is HomeScaffoldColors) return false

        if (containerColor != other.containerColor) return false
        if (navigationIconContentColor != other.navigationIconContentColor) return false
        if (titleContentColor != other.titleContentColor) return false
        if (actionIconContentColor != other.actionIconContentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + navigationIconContentColor.hashCode()
        result = 31 * result + titleContentColor.hashCode()
        result = 31 * result + actionIconContentColor.hashCode()
        return result
    }
}
