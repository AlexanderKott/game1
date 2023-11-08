package ru.mobiledevelopment.funnytictactoe.view

import android.graphics.Point
import android.view.View
import ru.mobiledevelopment.funnytictactoe.R
import ru.mobiledevelopment.funnytictactoe.logic.Consts


fun computerUI(lcl: View.OnLongClickListener, gfh: GameFieldHelper) {
    gfh.spawn(gfh.binding.cellXSpawn, R.drawable.x_sign, Consts.x, lcl)
    gfh.binding.moveOinfo.text = gfh.binding.root.context.getString(R.string.versusPC) 
    gfh.binding.moveXinfo.text = gfh.binding.root.context.getString(R.string.yourMove)
    gfh.spawnComputerIcon()
}


fun humanUI(lcl: View.OnLongClickListener, gfh: GameFieldHelper) {
    gfh.spawn(gfh.binding.cellOSpawn, R.drawable.o_sign, Consts.o, lcl)
    gfh.spawn(gfh.binding.cellXSpawn, R.drawable.x_sign, Consts.x, lcl)
    gfh.binding.moveXinfo.text = gfh.binding.root.context.getString(R.string.yourMove)
    gfh.binding.moveOinfo.text = gfh.binding.root.context.getString(R.string.yourMove)
}


class OTag(val number: Int, val point: Point)