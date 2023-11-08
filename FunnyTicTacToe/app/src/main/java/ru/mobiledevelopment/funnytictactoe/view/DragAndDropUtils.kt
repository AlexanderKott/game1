package ru.mobiledevelopment.funnytictactoe.view

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.animation.AnimationUtils
import ru.mobiledevelopment.funnytictactoe.R
import ru.mobiledevelopment.funnytictactoe.databinding.FragmentGameFieldBinding
import ru.mobiledevelopment.funnytictactoe.logic.GameConfiguration


class DrugAndDropHandler(
    val binding: FragmentGameFieldBinding,
    val gConfiguration: GameConfiguration,
) : View.OnDragListener, View.OnLongClickListener {

    private var iventsCount: Int = 0

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        val action = event?.action;
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    v?.background?.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
                    v?.invalidate();
                    return true;
                }
                return false
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                v?.background?.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                v?.invalidate();
                return true;
            }
            DragEvent.ACTION_DRAG_LOCATION ->
                return true;

            DragEvent.ACTION_DRAG_EXITED -> {
                v?.background?.clearColorFilter();
                v?.invalidate()
                return true;
            }
            DragEvent.ACTION_DROP -> {
                v?.background?.clearColorFilter();
                v?.invalidate();
                gConfiguration.checkDropCheating(event, v)
                binding.hint.visibility = View.INVISIBLE

                val sign = event.clipData.getItemAt(0).text.toString()
                gConfiguration.displayAnimation(sign,
                    {
                        binding.moveOinfo.animation =
                            AnimationUtils.loadAnimation(binding.root.context, R.anim.blink)
                        binding.moveXinfo.clearAnimation()
                    },
                    {
                        binding.moveXinfo.animation =
                            AnimationUtils.loadAnimation(binding.root.context, R.anim.blink)
                        binding.moveOinfo.clearAnimation()
                    }
                )

                return true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                v?.background?.clearColorFilter();
                v?.invalidate();
                if (event.result) {
                    gConfiguration.spawnPlayersSigns(this)
                    if (iventsCount < 8) {
                        iventsCount++
                    } else {
                        gConfiguration.doGamePlay()
                        iventsCount = 0
                    }
                }
                return true
            }

            else -> {
                Log.e("DragDrop", "Unknown action type received by OnDragListener.");
            }
        }
        return false;
    }


    override fun onLongClick(v: View?): Boolean {
        val item = ClipData.Item(v?.tag as CharSequence)
        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(v.tag.toString(), mimeTypes, item)
        val dragshadow = View.DragShadowBuilder(v)
        v.startDrag(data, dragshadow, v, 0)
        return true
    }


}