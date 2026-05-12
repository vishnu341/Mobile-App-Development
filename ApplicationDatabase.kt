package com.example.fitmeappfinalllimplementationv1.data.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(
    tableName = "history"

)

data class History (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "historyId") var historyId: Int = 0,
    @ColumnInfo(name = "goalsId") var goalsId: Int = 0,
    @ColumnInfo(name="goalsTitle") val goalsTitle: String,
    @ColumnInfo(name="goalsDate") val goalsDate : Int?,
    @ColumnInfo(name="goalsMonth") val goalsMonth: Int?,
    @ColumnInfo(name="goalsYear") val goalsYear: Int?,
    @ColumnInfo(name="stepsCount") var stepsCount:  Int=0,
    @ColumnInfo(name="targetSteps") val targetSteps: Int=0,
    @ColumnInfo(name="isGoalEnabled")  val isGoalEnabled:Int=0,
    @ColumnInfo(name="uniqueId") var uniqueId: String
        )
    //: Parcelable
{
    val localDate: LocalDate
        get() = LocalDate.of(goalsYear ?: 0, goalsMonth?:1, goalsDate ?: 1)



    }

