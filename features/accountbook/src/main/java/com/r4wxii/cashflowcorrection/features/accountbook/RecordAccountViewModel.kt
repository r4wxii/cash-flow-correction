package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

sealed class RecordAccountState {
    object Init : RecordAccountState()
    data class Fetched(val account: Account) : RecordAccountState()
    object RecordableData : RecordAccountState()
}

sealed class RecordAccountEvent {
    class InputDate(val date: String) : RecordAccountEvent()
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

    fun handleEvent(event: RecordAccountEvent) {
        when (event) {
            is RecordAccountEvent.InputDate -> {
                account.value = account.value.copy(date = LocalDate.parse(event.date))
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
            val account = useCase.getAccount(id)
            _state.value = RecordAccountState.Fetched(account)
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