package dev.propoc.moneysaver.saving.presentation.upcoming_payment_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class UpcomingPaymentsDetailsViewModel: ViewModel() {

    private val _state = MutableStateFlow(UpcomingPaymentState())
    val state = _state
    .onStart {
    }
    .stateIn(
    viewModelScope,
    SharingStarted.WhileSubscribed(5000L),
    _state.value
    )

    fun onAction(action: UpcomingPaymentDetailsAction) {
        when(action) {
            is UpcomingPaymentDetailsAction.OnBackClick -> {}
            is UpcomingPaymentDetailsAction.OnSelectedUpcomingPaymentChange -> {
                _state.update {
                    it.copy(
                        selectedUpcomingPayment = action.upcomingPayment
                    )
                }
            }
            is UpcomingPaymentDetailsAction.OnEdit -> {}
            is UpcomingPaymentDetailsAction.OnSaveClick -> {}
        }
    }
}