package pro.jayeshseth.madappmodules

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pro.jayeshseth.commoncomponents.HomeScaffold
import pro.jayeshseth.commoncomponents.InteractiveButton
import pro.jayeshseth.commoncomponents.StatusBarAwareThemedColumn
import pro.jayeshseth.madappmodules.ui.theme.MadAppModulesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MadAppModulesTheme {
                SampleUI()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleUI() {
    val verticalScrollState = rememberScrollState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    HomeScaffold(
        innerScrollState = verticalScrollState,
        topAppBarScrollBehavior = scrollBehavior,
        title = {
            Text(text = "Sample App")
        }
    ) {
        StatusBarAwareThemedColumn(
            statusBarColor = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(50) {
                InteractiveButton(
                    text = "Hello World!",
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}