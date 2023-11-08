package ru.mobiledevelopment.funnytictactoe.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import ru.mobiledevelopment.funnytictactoe.R


class AboutDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

    return AlertDialog.Builder(requireContext())
    .setMessage(getString(R.string.about))
    .setPositiveButton("ok")
    { _, _ ->

        val decorView = activity?.window?.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView?.systemUiVisibility = uiOptions
    }
    .create()
}
    companion object {
        const val TAG = "AboutDialog"
    }
}