package ru.mobiledevelopment.funnytictactoe.logic

import android.view.DragEvent
import android.view.View
import ru.mobiledevelopment.funnytictactoe.view.DrugAndDropHandler
import ru.mobiledevelopment.funnytictactoe.view.GameFieldHelper

interface GameConfiguration {
    fun displayAnimation(sing: String, anim1: () -> Unit, anim2: () -> Unit)
    fun spawnPlayersSigns(dndHelper: DrugAndDropHandler)
    fun doGamePlay()
    fun checkDropCheating(event: DragEvent?, v: View?)
    fun initUI( lcl : View.OnLongClickListener,  gfh : GameFieldHelper)
}