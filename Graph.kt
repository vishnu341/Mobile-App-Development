package com.example.fitmeappfinalllimplementationv1

import android.content.Context
import androidx.room.Room
import com.example.fitmeappfinalllimplementationv1.data.repository.GoalsRepository
import com.example.fitmeappfinalllimplementationv1.data.repository.HistoryRepository
import com.example.fitmeappfinalllimplementationv1.data.room.ApplicationDatabase


object Graph {

    lateinit var database: ApplicationDatabase

    private set

    val goalsRepository by lazy {
        GoalsRepository(
            goalsDao = database.GoalsDao()
        )
    }
    val historyRepository by lazy {
        HistoryRepository(
             historyDao = database.HistoryDao()
        )
    }


    fun provide(context: Context)
    {
        database= Room.databaseBuilder(context,ApplicationDatabase::class.java, "fitMeApplication.db").
                fallbackToDestructiveMigration().build()


    }
}