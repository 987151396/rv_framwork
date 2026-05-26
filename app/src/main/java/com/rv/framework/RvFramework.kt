package com.rv.framework

import android.app.Application
import com.rv.framework.utils.AppLogUtil
import com.rv.framework.utils.AppUtil
import com.rv.framework.utils.Utils

/**
 * RvFramework 初始化工具类
 */
object RvFramework {

    private var isDebugMode: Boolean = false
    private lateinit var application: Application

    /**
     * 初始化框架
     * @param app Application 实例
     * @param isDebug 是否为开发模式 (true: 开发模式, false: 正式模式)
     */
    fun init(app: Application, isDebug: Boolean) {
        this.application = app
        this.isDebugMode = isDebug

        // 1. 初始化内部 Utils (用于获取全局 Context)
        Utils.init(app)

        // 2. 配置日志开关 (假设 AppLogUtil 内部引用了 AppUtil.isDebug())
        // 如果 AppUtil 是我之前创建的占位类，我们可以在这里通过反射或直接赋值同步状态
        // 这里根据你项目现有的 AppLogUtil 逻辑进行配置
        AppLogUtil.allowD = isDebug
        AppLogUtil.allowE = isDebug
        AppLogUtil.allowI = isDebug
        AppLogUtil.allowV = isDebug
        AppLogUtil.allowW = isDebug
        AppLogUtil.allowWtf = isDebug

        AppLogUtil.i("RvFramework initialized in ${if (isDebug) "DEBUG" else "RELEASE"} mode")
    }

    /**
     * 获取 Application 实例
     */
    fun getApp(): Application {
        if (!::application.isInitialized) {
            throw IllegalStateException("RvFramework must be initialized before use. Call RvFramework.init(app, isDebug)")
        }
        return application
    }

    /**
     * 是否为调试模式
     */
    fun isDebug(): Boolean = isDebugMode
}
