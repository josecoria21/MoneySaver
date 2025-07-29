package dev.propoc.moneysaver.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.propoc.moneysaver.saving.presentation.SelectedUpcomingPaymentViewModel
import dev.propoc.moneysaver.saving.presentation.landing.LandingViewModel
import dev.propoc.moneysaver.saving.presentation.landing.LandingScreenRoot
import dev.propoc.moneysaver.saving.presentation.upcoming_payment_details.UpcomingPaymentsDetailsScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.SavingsGraph
        ) {
            navigation<Routes.SavingsGraph>(
                startDestination = Routes.Landing
            ) {
                composable<Routes.Landing> {
                    val viewModel = koinViewModel<LandingViewModel>()
                    val selectedUpcomingPaymentViewModel =
                        it.sharedKoinViewModel<SelectedUpcomingPaymentViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedUpcomingPaymentViewModel.onUpcomingPayment(null)
                    }

                    LandingScreenRoot(
                        viewModel = viewModel,
                        onUpcomingPaymentClick = { upcomingPayment ->
                            selectedUpcomingPaymentViewModel.onUpcomingPayment(upcomingPayment)
                            navController.navigate(
                                Routes.UpcomingPaymentsDetails
                            )
                        }
                    )
                }

                composable<Routes.UpcomingPaymentsDetails> {
                    val selectedUpcomingPaymentViewModel =
                        it.sharedKoinViewModel<SelectedUpcomingPaymentViewModel>(navController)
                    val selectedUpcomingPayment by
                    selectedUpcomingPaymentViewModel.selectedUpcomingPayment.collectAsStateWithLifecycle()

                    selectedUpcomingPayment?.let {
                        UpcomingPaymentsDetailsScreenRoot(
                            it,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}
