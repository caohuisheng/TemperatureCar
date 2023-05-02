package com.example.ViewModel

import android.os.Handler
import android.os.Message
import android.util.Log


class BlueToothHandler: Handler() {

    final val SendMessage = 0x10086

    lateinit var action:()->Unit
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when(msg.what) {
            this.SendMessage -> {
                Log.d("蓝牙传输","本机发送数据！！！！！！！！！")
                action()
            }
        }
    }

}