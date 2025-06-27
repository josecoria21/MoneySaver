package dev.propoc.moneysaver.saving.presentation

import androidx.lifecycle.ViewModel
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedUpcomingPaymentViewModel: ViewModel() {
    private val _selectedUpcomingPayment = MutableStateFlow<UpcomingPayment?>(null)
    val selectedUpcomingPayment = _selectedUpcomingPayment.asStateFlow()

    fun onUpcomingPayment(upcomingPayment: UpcomingPayment?) {
        _selectedUpcomingPayment.value = upcomingPayment
    }
}
