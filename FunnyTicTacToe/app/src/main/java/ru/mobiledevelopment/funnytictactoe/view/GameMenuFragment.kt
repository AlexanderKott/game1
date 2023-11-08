package ru.mobiledevelopment.funnytictactoe.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import ru.mobiledevelopment.funnytictactoe.R
import ru.mobiledevelopment.funnytictactoe.databinding.FragmentGameMenuBinding
import ru.mobiledevelopment.funnytictactoe.view.GameFieldFragment

class GameMenuFragment : Fragment() {
    lateinit var binding : FragmentGameMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return FragmentGameMenuBinding.inflate(inflater, container, false).root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameMenuBinding.bind(view)


        binding.PvP.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                setCustomAnimations(R.anim.bounce, R.anim.fade_out, R.anim.bounce, R.anim.fade_out)
                val bundle = bundleOf("secondPlayer" to "human")

                setReorderingAllowed(true)
                addToBackStack("pvp")
                replace<GameFieldFragment>(R.id.fragment_container_view, "tag", bundle)
            }
        }

        binding.versusPC.setOnClickListener {
            activity?.supportFragmentManager?.commit {
                val bundle = bundleOf("secondPlayer" to "computer")
                setReorderingAllowed(true)
                setCustomAnimations(R.anim.bounce, R.anim.fade_out, R.anim.bounce, R.anim.fade_out)
                addToBackStack("computer")
                replace<GameFieldFragment>(R.id.fragment_container_view, "tag", bundle)
            }
        }

        binding.about.setOnClickListener {
            AboutDialog().show(childFragmentManager, AboutDialog.TAG)
        }

        binding.soundChekbox.setOnCheckedChangeListener { compoundButton, checked ->
            setFragmentResult("soundRequest", bundleOf("soundKey" to checked))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.versusPC.animation = (AnimationUtils.loadAnimation(context, R.anim.slide_up))
        binding.PvP.animation = (AnimationUtils.loadAnimation(context, R.anim.slide_down))
    }

}