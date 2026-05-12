package com.example.fitmeappfinalllimplementationv1.presentation.settings

import UserPreferences
import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingVM(application: Application, context: Context) : AndroidViewModel(application) {
    val userPreferences = UserPreferences(context)

    private val _isHistoryPageEnabled = MutableStateFlow(userPreferences.isHistoryPageEnabled())
    val isHistoryPageEnabled: StateFlow<Boolean> = _isHistoryPageEnabled

    private val _isGoalPageEnabled = MutableStateFlow(userPreferences.isGoalPageEnabled())
    val isGoalPageEnabled: StateFlow<Boolean> = _isGoalPageEnabled

    fun setHistoryPageEnabled(enabled: Boolean) {
        viewModelScope.launch {
            _isHistoryPageEnabled.value = enabled
            userPreferences.setHistoryPageEnabled(enabled)
        }
    }

    fun setGoalPageEnabled(enabled: Boolean) {
        viewModelScope.launch {
            _isGoalPageEnabled.value = enabled
            userPreferences.setGoalPageEnabled(enabled)
        }
    }
}




/*


    private var _editToggleState:Boolean by mutableStateOf(false)

    var editToggleState:Boolean
    get(){return _editToggleState}
    set(value){
        userPreferences.setHistoryPageEnabled(value)
        _editToggleState = value
    }

    init{

    }
*/

