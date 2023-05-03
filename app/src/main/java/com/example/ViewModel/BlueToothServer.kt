package com.example.ViewModel

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.*
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.IOException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class BlueToothServer(context:Context) {
    private val UUID_car = "00001101-0000-1000-8000-00805F9B34FB"
    private val MAC_car = "00:23:02:31:10:33"

    private val context: Context

    private val handler = BlueToothHandler()

    private lateinit var gatt: BluetoothGatt

    private lateinit var blueDevice: BluetoothDevice

    private val listeners: ArrayList<BluetoothGattCharacteristic>

    private lateinit var blueSocket:BluetoothSocket

    companion object {
        @JvmStatic
        var bluetoothAdapter: BluetoothAdapter? = null
    }



    init{
        this.context = context
        listeners = ArrayList()
        if( bluetoothAdapter == null ) {
            val manager = ContextCompat.getSystemService(context, BluetoothManager::class.java)
            if (manager != null) {
                bluetoothAdapter = manager.adapter
            }
        }
    }

    fun checkBlueToothEnabled(): Boolean {
        return bluetoothAdapter?.isEnabled == true
    }

    @SuppressLint("MissingPermission")
    fun fetchMessage() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        if (pairedDevices != null) {
            for(device in pairedDevices) {
                if (device.address == MAC_car) {
                    blueDevice = device
                    blueSocket = device.createRfcommSocketToServiceRecord(UUID.fromString(UUID_car))
                }
            }
        }
    }



    private fun connect(device: BluetoothDevice) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        val deviceName = device.name
        val deviceHardwareAddress = device.address // MAC address
        Log.d("查看已连接的蓝牙信息-------->", "name: ${deviceName}, mac地址: ${deviceHardwareAddress}")
        gatt = device.connectGatt(context,false,object :BluetoothGattCallback() {
            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                super.onServicesDiscovered(gatt, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    kotlin.run {
                        val a = Runnable {
                            gatt?.getServices()?.forEach {
                                Log.d("GATT蓝牙服务", "UUID:${it.uuid}")
                                it.characteristics.forEach { charc ->
                                    Log.d("GATT蓝牙服务 -> charc", "UUID:${charc.uuid}")
                                    listeners.add(charc)
                                }
                            }
                        }
                        a.run()
                    }
                    Log.d("听众展示",listeners.toString())
                }
            }

            override fun onCharacteristicWrite(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                super.onCharacteristicWrite(gatt, characteristic, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    System.out.println("onCharacteristicWrite ------------------->write success:${characteristic?.value}");

                }
            }
        })
    }

    private fun isConnect(device: BluetoothDevice):Boolean {
        return try {
            val method = device.javaClass.getMethod("isConnected")
            method.invoke(device) as Boolean
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }

     @SuppressLint("MissingPermission")
     fun sendMessage(string: String) {
         if (blueDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
             Log.d("test", blueDevice.getName());
             try {
                 blueSocket =
                     blueDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(UUID_car));
             } catch (e1: Exception) {
                 Log.d("test", "socket not created");
                 e1.printStackTrace();
             }
             try {
                 blueSocket.connect()
                 blueSocket.outputStream.write(string.toByteArray())
             } catch (e: IOException) {
                 Log.e("test---",e.toString())
                 try {
                     blueSocket.close()
                     Log.d("test", "Cannot connect");
                 } catch (e1: IOException) {
                     Log.d("test", "Socket not closed");
                     e1.printStackTrace();
                 }
             }
         }
     }

}

class TempThread(handler: Handler, target: Runnable?): Thread(target) {
    private val handler: Handler

    init {
        this.handler = handler
    }
}
