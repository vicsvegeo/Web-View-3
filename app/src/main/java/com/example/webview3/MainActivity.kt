package com.example.webview3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.webview3.ui.theme.WebView3Theme
import com.facebook.ads.AudienceNetworkAds
//import com.google.android.gms.ads.MobileAds;
import com.appsflyer.AppsFlyerLib;
import com.onesignal.OneSignal;


class MainActivity : ComponentActivity() {
    private val AF_DEV_KEY = "ВАШИЯТ_DEV_KEY"
    private val ONESIGNAL_APP_ID = "ВАШИЯТ_APP_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //AudienceNetworkAds.initialize(this)
        //MobileAds.initialize(this);
        //AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, this);
        //AppsFlyerLib.getInstance().start(this);
        //OneSignal.initWithContext(this);
        //OneSignal.setAppId(ONESIGNAL_APP_ID);
        enableEdgeToEdge()
        setContent {
            WebView3Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.statusBars.asPaddingValues())
                ) {
                    WebViewScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(bottom = 10.dp)
    )
    Text(
        text = "Kosio mirishe",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WebView3Theme {
        Greeting("Android")
    }
}