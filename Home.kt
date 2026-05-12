package com.example.fitmeappfinalllimplementationv1.presentation.components


data class HistoryData(
    val progress:Int,
    val steps_taken:Int,
    val target:Int,

    )

val history_items= listOf(
    HistoryData( 20, 200, 1000),
    HistoryData( 20, 200, 1000),
    HistoryData( 20, 200, 1000)

)