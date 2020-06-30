package com.slutsenko.cocktailapp

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.slutsenko.cocktailapp.receiver.AirModeReceiver
import com.slutsenko.cocktailapp.receiver.BootReceiver
import com.slutsenko.cocktailapp.ui.dialog.BaseDialogFragment
import com.slutsenko.cocktailapp.ui.dialog.DialogButton
import com.slutsenko.cocktailapp.ui.dialog.DialogType

abstract class BaseActivity<DataBinding : ViewDataBinding> : AppCompatActivity() {

    protected open lateinit var dataBinding: DataBinding

    protected open fun configureDataBinding(binding: DataBinding) {}

    private val airModeReceiver = AirModeReceiver()
    private val bootReceiver = BootReceiver()
    private val log: String = "BaseLog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, myView())!!
        dataBinding.setLifecycleOwner(this@BaseActivity)
        activityCreated()
        configureDataBinding(dataBinding)

        Log.d(log, this::class.java.toString() + " OnCreate")

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


}