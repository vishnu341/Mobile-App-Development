package com.example.fitmeappfinalllimplementationv1.data.repository

/*


import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.fitmeappfinalllimplementationv1.GoalsBroadcastReceiver
*/
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.example.fitmeappfinalllimplementationv1.Graph
import com.example.fitmeappfinalllimplementationv1.data.entity.Goals
import com.example.fitmeappfinalllimplementationv1.data.entity.History

import com.example.fitmeappfinalllimplementationv1.data.room.GoalsDao
import com.example.fitmeappfinalllimplementationv1.data.room.HistoryDao
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.time.LocalDate
import kotlin.collections.*


/*import java.time.LocalDate

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.suspendCancellableCoroutine*/




class GoalsRepository(
    //private val goalsDao : GoalsDao
    private val goalsDao: GoalsDao = Graph.database.GoalsDao()
, private val historyDao : HistoryDao=Graph.database.HistoryDao()

) {






    fun getGoals(): Flow<List<Goals>> {
        return goalsDao.getAllGoals()
    }

    fun fetchGoals(): Goals?
    {
        return goalsDao.fetchAllGoals()
    }

    fun update(goal: Goals) {
        goalsDao.update(goal)
    }


    suspend fun deleteGoalAndHistory(goal: Goals) {
        withContext(Dispatchers.IO) {
            goalsDao.deleteGoal(goal)
            historyDao.deleteByGoalId(goal.goalsId)
        }
    }


    fun updateGoalEnabled(goalId: Int, isEnabled: Boolean) {
        try {

            historyDao.updateGoalEnabled(goalId, isEnabled)
        }
        catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error updating goals in history: ${e.message}")

        }

    }

    fun getGoalById(goalId: Int): LiveData<Goals?> {
        return goalsDao.getGoalById(goalId)
    }


    fun getActiveGoals(): Goals? {
        return goalsDao.getActiveGoals()
    }

    val today = LocalDate.now()
    suspend fun insert(goals: Goals): Long {
        try {
            return goalsDao.insert(goals)
        }
        catch (e: IllegalStateException) {
            e.printStackTrace()
            throw RuntimeException("Error updating goals in history: ${e.message}")
        }
    }

    fun selectGoalByTitle(goalsTitle: String): Goals? {
        return goalsDao.selectGoalByTitle(goalsTitle)
    }


}

