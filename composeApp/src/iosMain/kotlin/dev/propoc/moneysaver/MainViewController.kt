package dev.propoc.moneysaver

import androidx.compose.ui.window.ComposeUIViewController
import dev.propoc.moneysaver.app.App
import dev.propoc.moneysaver.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }