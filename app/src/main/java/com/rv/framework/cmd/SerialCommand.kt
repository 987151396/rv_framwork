package com.rv.framework.cmd


import com.rv.framework.cmd.base.AndroidDevice
import com.rv.framework.cmd.base.BaseCommand
import com.rv.framework.utils.AppLogUtil
import com.rv.framework.utils.StringUtils
import com.vi.vioserial.NormalSerial
import com.vi.vioserial.listener.OnSerialDataListener

abstract class SerialCommand : BaseCommand(), DeviceCtrlInterface {
    override fun start(): Int {
        try {
            val state = NormalSerial.instance()?.open(portStr, ibaudRate,false).apply {
                if(this == 0){
                    AppLogUtil.e("串口打开成功")
                    NormalSerial.instance()?.setSerialDataListener(object : OnSerialDataListener{
                        override fun onSend(hexData: ByteArray?) {
                            hexData?.let {
                                AppLogUtil.e("发送数据 ：" + StringUtils.bytes2HexString(it,it.size))
                            }
                        }

                        override fun onReceive(hexData: ByteArray?) {
                            hexData?.let {
                                AppLogUtil.e("收到数据 ：" + StringUtils.bytes2HexString(it,it.size) + " --- isCanBusUpdate：" + isCanBusUpdate.value)
                                if(isCanBusUpdate.value == false) {
                                    dataHandler?.onDataReceived(portStr, it, it.size)
                                }else{
                                    //把数据直接给总线盒升级页面
                                    dataHandler?.onCanBusUpdateDataReceived(portStr, it, it.size)
                                }
                            }
                        }

                        override fun onReceiveFullData(hexData: String?) {
                            hexData?.let {
                                AppLogUtil.e("onReceiveFullData ：$hexData")
                            }
                        }
                    })

                }else{
                    AppLogUtil.e("串口打开失败")
                }
            }
            return state!!
        }catch (e: Exception){
            //ToastUtil.showShort(e.message ?: "串口打开失败")
            e.printStackTrace()
        }
        return 0
    }

    override fun sendCom2Port(data: ByteArray) {
        //NormalSerial.instance()?.sendHex(data)
        //super.sendCom2Port(data)
        sendCom2Port(data,data.size)
    }

    override fun sendCom2Port(data: ByteArray, size: Int) {
        NormalSerial.instance()?.sendHex(data)
        //super.sendCom2Port(data,size)
    }

    override fun exit() {
        NormalSerial.instance()?.close()
    }

    override fun sendSquareCtrl(keyValue: Int, action: Int) {

    }

    override fun getSwcBtKeys(): Array<IntArray> {
        return arrayOf()
    }

    override fun intAndroidDeviceType(): AndroidDevice {
        return getAndroidDeviceType();
    }

    abstract fun getAndroidDeviceType(): AndroidDevice
}