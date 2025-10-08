package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.CutDateRepository
import com.sidor.procuts.data.models.CutDateDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MyCutsViewModel @Inject constructor(
    val cutDateRepository: CutDateRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            cutDateRepository.loadCuts()
        }
    }

    fun getCuts(): MutableStateFlow<MutableMap<Int, MutableStateFlow<CutDateDTO>>> = cutDateRepository.getStream()
}