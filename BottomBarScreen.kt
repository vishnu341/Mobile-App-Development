package com.example.fitmeappfinalllimplementationv1.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fitmeappfinalllimplementationv1.data.entity.Goals
import com.example.fitmeappfinalllimplementationv1.data.entity.History
import kotlinx.coroutines.flow.Flow

@Dao
@RewriteQueriesToDropUnusedColumns
abstract class HistoryDao {



    /*@Query("SELECT * FROM history WHERE goalsId=:goalsId ")
    abstract fun getGoalFromHistoryById(goalsId: Int):History?*/

    @Query("SELECT * FROM history where goalsDate<=:goalsDate ORDER BY goalsDate DESC")
    abstract fun getGoalFromHistorybyDate(goalsDate: Int): Flow<List<History>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(history: History)

    @Query("SELECT * FROM history WHERE goalsId = :goalsId")
    abstract fun getGoalById(goalsId: Int): Goals

    @Query("SELECT *, CASE WHEN goalsDate IS NULL OR goalsMonth IS NULL OR " +
            "goalsYear IS NULL THEN NULL " +
            "ELSE " +
            "substr('00' || goalsDate, -2, 2) || '/' || substr('00' || goalsMonth, -2, 2) || '/' || substr(goalsYear, -4, 4)" +
            " END AS date FROM history ORDER BY date ASC")
   // @Query("SELECT * FROM history ORDER BY goalsYear ASC, goalsMonth ASC, goalsDate ASC")

    abstract fun getAllHistoryItems():Flow<List<History>>




    @Query("SELECT * FROM history WHERE goalsDate=:goalsDate AND goalsMonth=:goalsMonth AND goalsYear=:goalsYear LIMIT 1")
    abstract fun getHistoryDataForDate(goalsDate: Int, goalsMonth: Int, goalsYear: Int): History?


    @Query("SELECT * FROM history WHERE goalsDate=:goalsDate")
    abstract fun getHistoryDataForSpecificDate(goalsDate: Int): History?

    @Query("SELECT * FROM history")
    abstract fun getAllHistory(): List<History>

    @Query("SELECT * FROM history")
    abstract fun getAllGoalsHistoryData(): LiveData<List<History>>

    @Query("SELECT * FROM history WHERE   goalsId=:goalsId  ORDER BY historyId DESC LIMIT 1")
    abstract fun getHistoryByGoalId(goalsId:Int ):History?



    @Query("SELECT * FROM history WHERE isGoalEnabled = 1 ORDER BY historyId DESC LIMIT 1")
    abstract fun getTargetStepsByGoalId():Int

    @Query("UPDATE goals SET isGoalEnabled = :isEnabled WHERE goalsId = :goalsId")
    abstract fun updateGoalEnabled(goalsId: Int, isEnabled: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(history: History)


    @Update
    abstract fun update(history: History)

    @Query("UPDATE history SET stepsCount = :stepsCount WHERE historyId = :historyId")
    abstract fun updateStepsCount(historyId: Int, stepsCount: Int)

   @Query("UPDATE history SET goalsTitle = :goalsTitle, isGoalEnabled = :isGoalEnabled, targetSteps = :targetSteps, stepsCount = :stepsCount, goalsDate = :goalsDate," +
           "goalsMonth = :goalsMonth, goalsYear = :goalsYear, uniqueId = :uniqueId WHERE goalsId = :goalsId")
   abstract fun updateGoalsInHistory(goalsId: Int, goalsTitle: String, isGoalEnabled: Int, targetSteps: Int, stepsCount: Int, goalsDate: Int, goalsMonth: Int, goalsYear: Int, uniqueId:String)

   @Query("SELECT * FROM history WHERE goalsId=:goalsId")
    abstract fun getCurrentActiveGoal(goalsId: Int): History?

/*    @Query("UPDATE goals SET isGoalEnabled = 1 WHERE id = :goalId")
    abstract  fun setActiveGoal(goalId: Long)*/


    @Query("SELECT * FROM history WHERE isGoalEnabled = 1 LIMIT 1")
    abstract fun getActiveGoal(): LiveData<History?>




   @Delete

    abstract fun deleteHistoryData(entity: History)

      @Query("DELETE FROM history WHERE goalsId = :goalsId")
      abstract fun deleteByGoalId(goalsId: Int)


      @Query("DELETE FROM history where goalsDate <= :goalsDate")
      abstract fun deleteByGoalsBeforeTodaysDate(goalsDate: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<History>)

}