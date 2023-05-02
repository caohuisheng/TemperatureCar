package com.example.ui

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class GuideActivity : AppCompatActivity() {
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                Log.d("test006", "${it.key} = ${it.value}")
            }
        }

    private var requestBluetooth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            //granted
        }else{
            //deny
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        getPemission()
        check()
        val navigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_activity_guide)
        navigationView.setupWithNavController(navController)
    }

    fun check() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            requestMultiplePermissions.launch(arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT))
        }
        else{
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            requestBluetooth.launch(enableBtIntent)
        }
    }
    
    @SuppressLint("InlinedApi")
    private fun getPemission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permission1 = ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
            val permission2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE) == PackageManager.PERMISSION_GRANTED
            val permission3 = ActivityCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
            if (!permission1 || !permission2 || !permission3) {
                ActivityCompat.requestPermissions(this, arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_CONNECT), 102)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 102 && grantResults[0] == RESULT_OK) {
            if(permissions.contains(Manifest.permission.BLUETOOTH_SCAN)){
                Toast.makeText(this,"获取扫描权限",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"获取扫描权限失败",Toast.LENGTH_SHORT).show()
            }

            if(permissions.contains(Manifest.permission.BLUETOOTH_ADVERTISE)){
                Toast.makeText(this,"获取调试权限",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"获取调试权限失败",Toast.LENGTH_SHORT).show()
            }

            if(permissions.contains(Manifest.permission.BLUETOOTH_CONNECT)){
                Toast.makeText(this,"获取连接权限",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"获取连接权限失败",Toast.LENGTH_SHORT).show()
            }

        }
    }

    
}