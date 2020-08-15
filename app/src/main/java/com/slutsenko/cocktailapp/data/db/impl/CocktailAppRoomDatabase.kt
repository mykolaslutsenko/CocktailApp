package com.slutsenko.cocktailapp.data.db.impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.slutsenko.cocktailapp.data.db.impl.dao.CocktailDao
import com.slutsenko.cocktailapp.data.db.impl.typeconverter.DateConverter
import com.slutsenko.cocktailapp.data.db.impl.typeconverter.StringListToStringConverter
import com.slutsenko.cocktailapp.data.db.model.CocktailDbModel
import com.slutsenko.cocktailapp.util.SingletonHolder

@Database(entities = [
    CocktailDbModel::class
], version = 1,
        exportSchema = false
)
@TypeConverters(DateConverter::class, StringListToStringConverter::class)
abstract class CocktailAppRoomDatabase : RoomDatabase() {

    abstract fun cocktailDao(): CocktailDao

    companion object : SingletonHolder<CocktailAppRoomDatabase, Context>({
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE ${Table.COCKTAIL} ADD COLUMN date INTEGER")
//            }
//        }


        Room
                .databaseBuilder(
                        it.applicationContext,
                        CocktailAppRoomDatabase::class.java,
                        "9091111111"
                )
                //.addMigrations(MIGRATION_1_2)
                //.fallbackToDestructiveMigration()
                .build()
    })
}