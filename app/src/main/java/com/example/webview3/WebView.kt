package com.example.webview3

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

import android.webkit.WebSettings



@Composable
fun WebViewScreen() {
    val context = LocalContext.current
    val webView = remember { WebView(context) }
    var showAlert by remember {
        mutableStateOf(false)
    }
    var errorName by remember {
        mutableStateOf("")
    }
    var errorText by remember {
        mutableStateOf("")
    }

    var canGoBack by remember {
        mutableStateOf(false)
    }

    // Enable JavaScript and other settings
    LaunchedEffect(Unit) {
        webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.cacheMode = WebSettings.LOAD_DEFAULT
            webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView,
                    request: WebResourceRequest,
                    error: WebResourceError
                ) {
                    if (error.errorCode ==
                        ERROR_UNSUPPORTED_AUTH_SCHEME ||
                        error.errorCode == ERROR_AUTHENTICATION ||
                        error.errorCode == ERROR_FILE ||
                        error.errorCode == ERROR_FILE_NOT_FOUND ||
                        error.errorCode == ERROR_HOST_LOOKUP ||
                        error.errorCode == ERROR_TIMEOUT ||
                        error.errorCode == -1
                    ) {
                    } else {
                        showAlert = true
                        errorName = "Error code ${error.errorCode}"
                        errorText = error.description.toString()
                    }

                }

                override fun onReceivedHttpError(
                    view: WebView,
                    request: WebResourceRequest,
                    errorResponse: WebResourceResponse
                ) {
                    if ((errorResponse.statusCode >= 500 || errorResponse.statusCode >= 404)
                        && errorResponse.statusCode != 451 &&
                        errorResponse.statusCode != 408 && errorResponse.statusCode != 429
                    ) {
                        showAlert = true
                        errorName = "Error code ${errorResponse.statusCode}"
                        errorText = errorResponse.responseHeaders.toString()
                    }
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    canGoBack = webView.canGoBack()
                }
            }
            loadUrl("https://psbattles.fandom.com/wiki/Tiering_System")
        }
    }
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = {
                Button(onClick = { showAlert = false }) {
                    Text(text = "Okay")
                }
            },
            title = { Text(text = errorName) },
            text = { Text(text = errorText) })
    }
    // Handle back button press
    BackHandler(enabled = canGoBack) {
        webView.goBack()
    }

    DisposableEffect(Unit) {
        onDispose {
            webView.destroy()
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { webView }
    )
}
