package ru.shiftlab.mvvmshiftlab.profile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProfileEntity::class], version = 1, exportSchema = false)
abstract class ProfileDatabase : RoomDatabase() {

    abstract val profileDao: ProfileDao

    companion object {

        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getInstance(context: Context): ProfileDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "profile_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}