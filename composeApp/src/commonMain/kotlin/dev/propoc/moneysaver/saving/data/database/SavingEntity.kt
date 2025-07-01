package dev.propoc.moneysaver.saving.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavingEntity(
    @PrimaryKey(autoGenerate = false) val id: String,

)