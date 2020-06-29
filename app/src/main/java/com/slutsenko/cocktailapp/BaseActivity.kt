package com.slutsenko.cocktailapp

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.slutsenko.cocktailapp.receiver.AirModeReceiver
import com.slutsenko.cocktailapp.receiver.BootReceiver
import com.slutsenko.cocktailapp.ui.dialog.BaseDialogFragment

abstract class BaseActivity : AppCompatActivity(),
        BaseDialogFragment.OnDialogFragmentClickListener<Cocktail>,
        BaseDialogFragment.OnDialogFragmentDismissListener<Cocktail> {
    private val airModeReceiver= AirModeReceiver()
    private val bootReceiver = BootReceiver()
    private val log: String = "BaseLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(log, this::class.java.toString() + " OnCreate")
        setContentView(myView())
        activityCreated()
    }

    protected abstract fun myView(): Int
    protected abstract fun activityCreated()

    override fun onStart() {
        super.onStart()
        Log.d(log, this::class.java.toString() + " OnStart")
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(bootReceiver, IntentFilter("android.intent.action.BOOT_COMPLETED"))
        registerReceiver(airModeReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
        Log.d(log, this::class.java.toString() + " OnResume")
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(airModeReceiver)
        unregisterReceiver(bootReceiver)
        Log.d(log, this::class.java.toString() + " OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(log, this::class.java.toString() + " OnStop")
    }

    override fun onDestroy() {

        super.onDestroy()
        Log.d(log, this::class.java.toString() + " OnDestroy")
    }

    override fun onBottomSheetDialogFragmentClick(dialog: DialogFragment, data: Cocktail?) {
        TODO("Not yet implemented")
    }

    override fun onBottomSheetDialogFragmentDismiss(dialog: DialogFragment, data: Cocktail?) {
        TODO("Not yet implemented")
    }


}