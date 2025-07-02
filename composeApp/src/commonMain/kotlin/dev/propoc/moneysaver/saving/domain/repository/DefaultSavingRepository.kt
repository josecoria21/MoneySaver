package dev.propoc.moneysaver.saving.domain.repository

import dev.propoc.moneysaver.core.domain.DataError
import dev.propoc.moneysaver.core.domain.EmptyResult
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment
import kotlinx.coroutines.flow.Flow

interface DefaultSavingRepository {
    suspend fun setUpcomingPayment(upcomingPayment: UpcomingPayment): EmptyResult<DataError.Local>
    fun getUpcomingPayments(): Flow<List<UpcomingPayment>>
}