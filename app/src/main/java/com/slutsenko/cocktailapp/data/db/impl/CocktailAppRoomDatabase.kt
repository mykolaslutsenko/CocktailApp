package com.slutsenko.cocktailapp.data.db.impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slutsenko.cocktailapp.data.db.impl.dao.CocktailDao
import com.slutsenko.cocktailapp.data.db.impl.dao.UserDao
import com.slutsenko.cocktailapp.data.db.impl.typeconverter.DateConverter
import com.slutsenko.cocktailapp.data.db.impl.typeconverter.StringListToStringConverter
import com.slutsenko.cocktailapp.data.db.model.CocktailDbModel
import com.slutsenko.cocktailapp.data.db.model.UserDbModel
import com.slutsenko.common.SingletonHolder

@Database(entities = [
    CocktailDbModel::class, UserDbModel::class
], version = 1,
        exportSchema = false
)
@TypeConverters(DateConverter::class, StringListToStringConverter::class)
abstract class CocktailAppRoomDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao
    abstract fun userDao(): UserDao

    companion object : SingletonHolder<CocktailAppRoomDatabase, Context>({
//        val MIGRATION_1_2: Migration = object: Migration(1,2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("alter table ${Table.COCKTAIL} add column date integer not null default 0")
//            }
//        }

        Room
                .databaseBuilder(
                        it.applicationContext,
                        CocktailAppRoomDatabase::class.java,
                        "data"
                )
                .fallbackToDestructiveMigration()
                //.addMigrations(MIGRATION_1_2)
                .build()
    })
}