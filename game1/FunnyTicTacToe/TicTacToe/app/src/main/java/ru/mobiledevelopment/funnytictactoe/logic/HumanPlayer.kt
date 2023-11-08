package ru.mobiledevelopment.funnytictactoe.logic

import android.view.DragEvent
import android.view.View
import ru.mobiledevelopment.funnytictactoe.view.DrugAndDropHandler
import ru.mobiledevelopment.funnytictactoe.view.GameFieldHelper
import ru.mobiledevelopment.funnytictactoe.R
import ru.mobiledevelopment.funnytictactoe.view.humanUI

class HumanPlayer(private var gfh: GameFieldHelper) : GameConfiguration {
    override fun displayAnimation(sing: String, anim1: () -> Unit, anim2: () -> Unit) {
        if (sing == Consts.x) {
            anim1.invoke()
        } else {
            anim2.invoke()
        }
    }

    override fun spawnPlayersSigns(dndHelper: DrugAndDropHandler) {
        gfh.spawn(gfh.binding.cellXSpawn, R.drawable.x_sign, Consts.x, dndHelper)
        gfh.spawn(gfh.binding.cellOSpawn, R.drawable.o_sign, Consts.o, dndHelper)
    }

    override fun doGamePlay() {
        with(gfh) {
            updateDataFromFieldtoArray(gameState)
            var message = calcGameResults(gameState)

            displayInfo(message)

            if (message == Consts.draw) {
                clearGameField()
                return
            }
            if (getWinnersRow(gameState).weHaveWinner) {
                playRowDisappearAnimationAndClearField(getWinnersRow(gameState)) {}
                return
            }
        }
    }


    override fun checkDropCheating(event: DragEvent?, v: View?) {
        gfh.acceptDrop(event, v)
    }

    override fun initUI(lcl: View.OnLongClickListener, gfh: GameFieldHelper) {
        humanUI(lcl, gfh)
    }
}