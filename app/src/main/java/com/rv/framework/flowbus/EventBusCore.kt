package com.rv.framework.flowbus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rv.framework.utils.AppLogUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlin.collections.set

/**
 * FlowBus核心类
 *
 * @Author holo
 * @Date 2022/3/4
 */
class EventBusCore : ViewModel() {

    /**
     * 正常事件
     */
    private val eventFlows: HashMap<String, MutableSharedFlow<Any>> = HashMap()

    /**
     * 粘性事件
     */
    private val stickyEventFlows: HashMap<String, MutableSharedFlow<Any>> = HashMap()

    private fun getEventFlow(eventName: String, isSticky: Boolean): MutableSharedFlow<Any> {
        return if (isSticky) {
            stickyEventFlows[eventName]
        } else {
            eventFlows[eventName]
        } ?: MutableSharedFlow<Any>(
            replay = if (isSticky) 1 else 0,
            extraBufferCapacity = Int.MAX_VALUE //避免挂起导致数据发送失败
        ).also {
            if (isSticky) {
                stickyEventFlows[eventName] = it
            } else {
                eventFlows[eventName] = it
            }
        }
    }

    fun <T : Any> observeEvent(
        lifecycleOwner: LifecycleOwner,
        eventName: String,
        minState: Lifecycle.State,
        dispatcher: CoroutineDispatcher,
        isSticky: Boolean,
        onReceived: (T) -> Unit
    ): Job {
        AppLogUtil.d("FlowBus","observe Event:$eventName")
        return lifecycleOwner.launchWhenStateAtLeast(minState) {
            getEventFlow(eventName, isSticky).collect { value ->
                this.launch(dispatcher) {
                    invokeReceived(value, onReceived)
                }
            }
        }
    }
    /*fun <T : Any> observeEvent(
        lifecycleOwner: BaseVmFragment<*>,
        eventName: String,
        minState: Lifecycle.State,
        dispatcher: CoroutineDispatcher,
        isSticky: Boolean,
        onReceived: (T) -> Unit
    ): Job {
        "observe Event:$eventName".logw("FlowBus")
        return lifecycleOwner.launchWhenStateAtLeast(minState) {
            getEventFlow(eventName, isSticky).collect { value ->
                //if(lifecycleOwner.isVisibleToUser) {
                    this.launch(dispatcher) {
                        invokeReceived(value, onReceived)
                    }
                //}
            }
        }
    }*/

    suspend fun <T : Any> observeWithoutLifecycle(
        eventName: String,
        isSticky: Boolean,
        onReceived: (T) -> Unit
    ) {
        getEventFlow(eventName, isSticky).collect { value ->
            invokeReceived(value, onReceived)
        }
    }


    fun postEvent(eventName: String, value: Any, timeMillis: Long) {
        AppLogUtil.d("FlowBus","post Event:$eventName")
        listOfNotNull(
            getEventFlow(eventName, false),
            getEventFlow(eventName, true)
        ).forEach { flow ->
            viewModelScope.launch {
                delay(timeMillis)
                flow.emit(value)
            }
        }
    }


    fun removeStickEvent(eventName: String) {
        stickyEventFlows.remove(eventName)
    }

    fun clearStickEvent(eventName: String) {
        stickyEventFlows[eventName]?.resetReplayCache()
    }


    private fun <T : Any> invokeReceived(value: Any, onReceived: (T) -> Unit) {
        try {
            onReceived.invoke(value as T)
        } catch (e: ClassCastException) {
            AppLogUtil.e("FlowBus : class cast error on message received: $value", e)
        } catch (e: Exception) {
            AppLogUtil.e("FlowBus : error on message received: $value", e)
        }
    }


    fun getEventObserverCount(eventName: String): Int {
        val stickyObserverCount = stickyEventFlows[eventName]?.subscriptionCount?.value ?: 0
        val normalObserverCount = eventFlows[eventName]?.subscriptionCount?.value ?: 0
        return stickyObserverCount + normalObserverCount
    }

}