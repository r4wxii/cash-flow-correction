package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

abstract class RecordAccountViewModel : ViewModel() {
    abstract val date: MutableLiveData<String>
    abstract val quantity: MutableLiveData<String>
    abstract val category: MutableLiveData<String>
    abstract val subCategory: MutableLiveData<String>
    abstract val comment: MutableLiveData<String>
    abstract val recordEnabled: LiveData<Boolean>

    abstract fun getAccount(id: Int)
    abstract fun saveAccount()
}

class RecordAccountViewModelFactory @Inject constructor(
    private val useCase: AccountUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecordAccountViewModelImpl(useCase) as T
    }
}

class RecordAccountViewModelImpl @Inject constructor(
    private val useCase: AccountUseCase
) : RecordAccountViewModel() {
    private val account = MutableStateFlow(Account(0, 0, LocalDate.now(), "", null, null))
    override val date = MutableLiveData<String>()
    override val quantity = MutableLiveData<String>()
    override val category = MutableLiveData<String>()
    override val subCategory = MutableLiveData<String>()
    override val comment = MutableLiveData<String>()
    override val recordEnabled = liveData(context = viewModelScope.coroutineContext) {
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

    override fun getAccount(id: Int) {
        viewModelScope.launch {
            val account = useCase.getAccount(id)
            this@RecordAccountViewModelImpl.account.value.copy(
                id = account.id,
                quantity = account.quantity,
                date = account.date,
                category = account.category,
                subCategory = account.subCategory,
                comment = account.comment,
            )
        }
    }

    override fun saveAccount() {
        viewModelScope.launch {
            useCase.insert(account.value)
        }
    }
}