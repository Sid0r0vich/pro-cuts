package com.sidor.procuts.ui.components

import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KProperty

class MutableStateFlowDelegate<T>(
    private val flow: MutableStateFlow<T>
) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = flow.value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        flow.value = value
    }
}