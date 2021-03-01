package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RecordAccountViewModel @Inject constructor(
    private val useCase: AccountUseCase
) : ViewModel() {
    private val account = MutableStateFlow(Account.empty())
    val quantity = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val subCategory = MutableLiveData<String>()
    val comment = MutableLiveData<String>()
    val recordEnabled = liveData(context = viewModelScope.coroutineContext) {
        emit(false)

        combine(
            date.asFlow(),
            quantity.asFlow(),
            category.asFlow()
        ) { date, quantity, category ->
            date.isNotBlank() && quantity.isNotBlank() && category.isNotBlank()
        }.collect { emit(it) }
    }

    init {
        viewModelScope.launch {
            date.asFlow().collect {
                account.value.copy(date = LocalDate.parse(it))
            }
            quantity.asFlow().collect {
                account.value.copy(quantity = it.toInt())
            }
            category.asFlow().collect {
                account.value.copy(category = it)
            }
            subCategory.asFlow().collect {
                account.value.copy(subCategory = it)
            }
            comment.asFlow().collect {
                account.value.copy(comment = it)
            }
        }
    }

    fun getAccount(id: Int) {
        viewModelScope.launch {
            val account = useCase.getAccount(id)
            this@RecordAccountViewModel.account.value.copy(
                id = account.id,
                quantity = account.quantity,
                date = account.date,
                category = account.category,
                subCategory = account.subCategory,
                comment = account.comment,
            )
        }
    }

    fun saveAccount() {
        viewModelScope.launch {
            useCase.insert(account.value)
        }
    }
}