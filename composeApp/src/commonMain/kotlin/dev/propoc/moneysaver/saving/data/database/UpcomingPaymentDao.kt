package dev.propoc.moneysaver.saving.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingPaymentDao {

    @Upsert
    suspend fun insertUpcomingPayment(upcomingPayment: UpcomingPaymentEntity)

    @Query("SELECT * FROM UpcomingPaymentEntity")
    fun getUpcomingPayments(): Flow<List<UpcomingPaymentEntity>>

    @Query("SELECT * FROM UpcomingPaymentEntity WHERE id = :id")
    suspend fun getUpcomingPaymentById(id: String): UpcomingPaymentEntity?


}