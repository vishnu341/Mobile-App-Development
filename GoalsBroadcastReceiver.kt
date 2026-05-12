package com.example.fitmeappfinalllimplementationv1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.fitmeappfinalllimplementationv1.data.entity.History
import com.example.fitmeappfinalllimplementationv1.data.repository.GoalsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class GoalsBroadcastReceiver
    //: BroadcastReceiver() {
/*
    override fun onReceive(context: Context, intent: Intent) {
        val goalsId = intent.getLongExtra(EXTRA_GOALS_ID, -1)
        if (goalsId != -1L) {
            val repository = GoalsRepository(
                Graph.database.GoalsDao(),
                Graph.database.HistoryDao()
            )

            CoroutineScope(Dispatchers.IO).launch {
                val goal = repository.getGoalById(goalsId)
                if (goal != null) {
                    val today = LocalDate.now()
                    val history = History(
                        goalsId = goalsId.toInt(),
                        goalsTitle = goal.goalsTitle,
                        goalsDate = today.dayOfMonth,
                        goalsMonth = today.monthValue,
                        goalsYear = today.year,
                        stepsCount = 0,
                        targetSteps = goal.targetSteps,
                        isGoalEnabled = goal.isGoalEnabled
                    )
                    repository.insertHistory(history)
                }
            }
        }*/




   /* companion object {
        const val EXTRA_GOALS_ID = "com.example.fitmeappfinalllimplementationv1.EXTRA_GOALS_ID"
    }
}
*/