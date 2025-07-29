package dev.propoc.moneysaver.app

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object SavingsGraph: Routes

    @Serializable
    data object Landing: Routes

    @Serializable
    data object UpcomingPaymentsDetails: Routes
}
