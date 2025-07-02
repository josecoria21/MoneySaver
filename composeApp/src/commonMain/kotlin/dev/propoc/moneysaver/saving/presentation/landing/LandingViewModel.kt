package dev.propoc.moneysaver.saving.presentation.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.propoc.moneysaver.saving.domain.repository.DefaultSavingRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LandingViewModel(
    private val repository: DefaultSavingRepository
): ViewModel() {

    private var observeUpcomingPaymentsJob: Job? = null

    private val _state = MutableStateFlow(LandingState())
    val state = _state
        .onStart {
            observeUpcomingPayments()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun observeUpcomingPayments() {
        observeUpcomingPaymentsJob?.cancel()
        observeUpcomingPaymentsJob = repository
            .getUpcomingPayments()
            .onEach { upcomingPayments ->
                _state.update { it.copy(
                    upcomingPayments = upcomingPayments
                ) }
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: LandingAction) {
        when(action) {
            is LandingAction.OnUpcomingPaymentClick -> {

            }
            is LandingAction.OnAddUpcomingPaymentClick -> {
                viewModelScope.launch {
                    repository.setUpcomingPayment(upcomingPayment = action.upcomingPayment)

                }
            }
        }
    }

    fun getUpcomingPayments() {
        viewModelScope.launch {
            repository.getUpcomingPayments()
        }
    }
}