package com.example.fitmeappfinalllimplementationv1.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "goals",

   )
data class Goals(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "goalsId") val goalsId: Int = 0,
    @ColumnInfo(name="goalsTitle") val goalsTitle: String,
    @ColumnInfo(name="targetSteps") val targetSteps: Int,
    @ColumnInfo(name="isGoalEnabled") var isGoalEnabled:Int=0,
    @ColumnInfo(name="uniqueId") var uniqueId: String



) {


}