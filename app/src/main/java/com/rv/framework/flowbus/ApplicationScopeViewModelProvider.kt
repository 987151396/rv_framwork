package com.rv.framework.flowbus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.rv.framework.utils.Utils

/**
 * Application级别的ViewModel功能
 *
 * @Author holo
 * @Date 2022/3/4
 */
object ApplicationScopeViewModelProvider : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore
        get() = ViewModelStore()

    private val mApplicationProvider: ViewModelProvider by lazy {
        ViewModelProvider(
            ApplicationScopeViewModelProvider,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Utils.getApp())
        )
    }

    fun <T : ViewModel> getApplicationScopeViewModel(modelClass: Class<T>): T {
        return mApplicationProvider[modelClass]
    }
}
