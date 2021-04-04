package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.TemporalAccessor
import javax.inject.Inject

sealed class RecordAccountState {
    object Init : RecordAccountState()
    data class RecordableData(val account: Account, val isRecordable: Boolean) : RecordAccountState()
}

sealed class RecordAccountEvent {
    class InputDate(val date: Long) : RecordAccountEvent()
    class InputQuantity(val quantity: String) : RecordAccountEvent()
    class InputCategory(val category: String) : RecordAccountEvent()
    class InputSubCategory(val subCategory: String) : RecordAccountEvent()
    class InputComment(val comment: String) : RecordAccountEvent()
}

@HiltViewModel
class RecordAccountViewModel @Inject constructor(
    private val useCase: AccountUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<RecordAccountState> =
        MutableStateFlow(RecordAccountState.Init)
    val state: LiveData<RecordAccountState> = _state.asLiveData()
    val account = MutableStateFlow(Account.empty())

    init {
        viewModelScope.launch {
            account.collect { account ->
                _state.value = RecordAccountState.RecordableData(account, isRecordable = account.quantity > 0 && account.category.isNotBlank())
            }
        }
    }

    fun handleEvent(event: RecordAccountEvent) {
        when (event) {
            is RecordAccountEvent.InputDate -> {
                account.value = account.value.copy(date = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.date), ZoneId.systemDefault()).toLocalDate())
            }
            is RecordAccountEvent.InputQuantity -> {
                account.value = account.value.copy(quantity = event.quantity.toIntOrNull() ?: 0)
            }
            is RecordAccountEvent.InputCategory -> {
                account.value = account.value.copy(category = event.category)
            }
            is RecordAccountEvent.InputSubCategory -> {
                account.value = account.value.copy(subCategory = event.subCategory)
            }
            is RecordAccountEvent.InputComment -> {
                account.value = account.value.copy(comment = event.comment)
            }
        }
    }

    fun getAccount(id: Int) {
        if (id < 1) {
            _state.value = RecordAccountState.RecordableData(account.value, false)
            return
        }
        viewModelScope.launch {
            val account = useCase.getAccount(id)
            _state.value = RecordAccountState.RecordableData(account, true)
        }
    }

    fun saveAccount() {
        if (_state.value is RecordAccountState.RecordableData) {
            viewModelScope.launch {
                useCase.insert(account.value)
            }
        }
    }
}