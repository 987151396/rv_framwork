package com.rv.framwork.cmd.base

import com.rv.framwork.event.EventLiveData

abstract class BaseCommand : Command
{
    var dataHandler : DataHandler? = null

    val isCanBusUpdate = EventLiveData<Boolean>().apply {
        this.value = false
    }

    //protected var portStr = "/dev/ttyS7"
    //protected var portStr = "/dev/ttyS4"
    //protected var portStr = "/dev/ttyHS1"//讯族
    protected var portStr = "/dev/ttyS1"
    companion object{
        //var ibaudRate = 38400
        var ibaudRate = 115200
    }

    override fun sendCom2Port(data: ByteArray) {
        sendCom2Port(data,data.size)
    }

    override fun sendCom2Port(data: ByteArray, size: Int) {

    }

    open fun isCanBusUpdate(values : Boolean){
        isCanBusUpdate.value = values
    }

    abstract fun exit()
    abstract fun start():Int

    interface DataHandler {
        fun onDataReceived(comName: String?, buffer: ByteArray?, size: Int)
        fun onDataSend(buffer: ByteArray?)
    }

}

enum class AndroidDevice{
    AndroidZXW,
    AndroidFYT,
    AndroidXT,
}