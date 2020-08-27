package com.slutsenko.cocktailapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.slutsenko.cocktailapp.data.db.Table

@Entity(tableName = Table.USER)
data class UserDbModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 1L,

    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "last_name")
    val lastName: String = "",

    @ColumnInfo(name = "email")
    val email: String = "",

    @ColumnInfo(name = "avatar")
    val avatar: String? = null
)