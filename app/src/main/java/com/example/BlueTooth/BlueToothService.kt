package com.example.BlueTooth

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback

class BlueToothService {
    val gattCallBack =  object: BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (status == BluetoothGatt.GATT_SUCCESS) {

            }
        }
    }
}