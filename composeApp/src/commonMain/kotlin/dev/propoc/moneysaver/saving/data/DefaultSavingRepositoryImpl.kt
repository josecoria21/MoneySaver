package dev.propoc.moneysaver.saving.data

import androidx.sqlite.SQLiteException
import dev.propoc.moneysaver.core.domain.DataError
import dev.propoc.moneysaver.core.domain.EmptyResult
import dev.propoc.moneysaver.core.domain.Result
import dev.propoc.moneysaver.saving.data.database.UpcomingPaymentDao
import dev.propoc.moneysaver.saving.data.mapper.toUpcomingPayment
import dev.propoc.moneysaver.saving.data.mapper.toUpcomingPaymentEntity
import dev.propoc.moneysaver.saving.domain.model.UpcomingPayment
import dev.propoc.moneysaver.saving.domain.repository.DefaultSavingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultSavingRepositoryImpl(
    private val upcomingPaymentDao: UpcomingPaymentDao
) : DefaultSavingRepository {
    override fun getUpcomingPayments(): Flow<List<UpcomingPayment>> {
        return upcomingPaymentDao.getUpcomingPayments()
            .map { upcomingPaymentsEntities ->
                upcomingPaymentsEntities.map { upcomingPaymentEntity ->
                    upcomingPaymentEntity.toUpcomingPayment()
                }
            }
    }

    override suspend fun setUpcomingPayment(upcomingPayment: UpcomingPayment): EmptyResult<DataError.Local> {
        return try {
            upcomingPaymentDao.insertUpcomingPayment(upcomingPayment.toUpcomingPaymentEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }
}