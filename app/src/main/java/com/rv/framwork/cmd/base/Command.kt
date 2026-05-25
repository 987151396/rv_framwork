package com.rv.framwork.cmd.base

interface Command {
    fun sendCom2Port(data : ByteArray)
    fun sendCom2Port(data : ByteArray, size : Int)
}