package com.example.fitmeappfinalllimplementationv1

import android.app.Application


class FitMeApplication : Application()  {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)

    }



}