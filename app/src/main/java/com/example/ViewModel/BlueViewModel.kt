package com.example.ViewModel

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import java.lang.Exception
import java.util.*

class BlueViewModel(application: Application) : AndroidViewModel(application) {
    private val server: BlueToothServer

    init {
        server = BlueToothServer(application)
        server.fetchMessage()
    }


    fun sendMessage(string: String) {
        server.sendMessage(string)
    }

}
