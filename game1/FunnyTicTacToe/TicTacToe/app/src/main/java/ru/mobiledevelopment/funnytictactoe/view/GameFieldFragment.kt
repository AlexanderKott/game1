package ru.mobiledevelopment.funnytictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.mobiledevelopment.funnytictactoe.R
import ru.mobiledevelopment.funnytictactoe.databinding.FragmentGameFieldBinding
import ru.mobiledevelopment.funnytictactoe.logic.ComputerPlayer
import ru.mobiledevelopment.funnytictactoe.logic.GameState
import ru.mobiledevelopment.funnytictactoe.logic.HumanPlayer
import ru.mobiledevelopment.funnytictactoe.logic.initGameField


class GameFieldFragment : Fragment() {

    private lateinit var dndHelper: DrugAndDropHandler
    private lateinit var gameState : GameState

    private var _binding: FragmentGameFieldBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGameFieldBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(vie: View, savedInstanceState: Bundle?) {
        super.onViewCreated(vie, savedInstanceState)

        val sounds = context?.let { GameSoundPool(it) }

        gameState = initGameField()
        val gfh = GameFieldHelper(sounds, binding, inflateToasts(), gameState, resources)
        val arg = requireArguments().getString("secondPlayer")
        val gameConfiguration = if (arg == "computer")  ComputerPlayer(gfh) else HumanPlayer(gfh)
        dndHelper = DrugAndDropHandler(binding, gameConfiguration)
        gameConfiguration.initUI(dndHelper, gfh)
        gfh.initFieldLayout(binding, dndHelper)

        binding.moveXinfo.animation = (AnimationUtils.loadAnimation(context, R.anim.blink))
    }




    private fun inflateToasts(): ToastsContainer {
        val inflater = layoutInflater
        val container: ViewGroup? = activity?.findViewById(R.id.custom_toast_layout)

        val layout: ViewGroup = inflater.inflate(R.layout.toast, container) as ViewGroup
        val toastText: TextView = layout.findViewById(R.id.text)

        val layout2: ViewGroup = inflater.inflate(R.layout.toast, container) as ViewGroup
        val toastText2: TextView = layout2.findViewById(R.id.text)

        return ToastsContainer(toastText, layout, toastText2, layout2)
    }
}