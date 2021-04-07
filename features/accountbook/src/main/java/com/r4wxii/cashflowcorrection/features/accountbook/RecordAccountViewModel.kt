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
    data class Fetched(val account: Account) : RecordAccountState()
    data class RecordableData(val isRecordable: Boolean) : RecordAccountState()
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
        MutableStateFlow(RecordAccountState.RecordableData(false))
    val state: LiveData<RecordAccountState> = _state.asLiveData()
    val account = MutableStateFlow(Account.empty())

    init {
        viewModelScope.launch {
            account.collect { account ->
                _state.value = RecordAccountState.RecordableData(account.quantity > 0 && account.category.isNotBlank())
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
        if (id < 1) return
        viewModelScope.launch {
            account.value = useCase.getAccount(id)
            _state.value = RecordAccountState.Fetched(account.value)
        }
    }

    fun saveAccount() {
        if (_state.value is RecordAccountState.Fetched || _state.value is RecordAccountState.RecordableData) {
            viewModelScope.launch {
                useCase.insert(account.value)
            }
        }
    }
}