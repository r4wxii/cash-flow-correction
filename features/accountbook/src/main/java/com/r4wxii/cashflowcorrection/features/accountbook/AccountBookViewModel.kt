package com.r4wxii.cashflowcorrection.features.accountbook

import androidx.lifecycle.*
import com.r4wxii.cashflowcorrection.domain.model.Account
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

abstract class AccountBookViewModel : ViewModel() {
    abstract val accountData: LiveData<List<Account>>
    abstract val date: MutableLiveData<String>
    abstract val quantity: MutableLiveData<String>
    abstract val category: MutableLiveData<String>
    abstract val subCategory: MutableLiveData<String>
    abstract val comment: MutableLiveData<String>
    abstract val recordEnabled: LiveData<Boolean>

    abstract fun saveAccount()
}

class AccountBookViewModelFactory @Inject constructor(
    private val useCase: AccountUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountBookViewModelImpl(useCase) as T
    }
}

class AccountBookViewModelImpl @Inject constructor(
    private val useCase: AccountUseCase
) : AccountBookViewModel() {
    override val accountData: LiveData<List<Account>> = useCase.data.asLiveData()
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
            useCase.getThisMonthAccounts()
        }
    }

    override fun saveAccount() {
        viewModelScope.launch {
            useCase.insert(
                Account.createNewItem(
                    quantity.value?.toInt() ?: 0,
                    LocalDate.parse(date.value),
                    category.value.orEmpty(),
                    subCategory.value,
                    comment.value
                )
            )
        }
    }
}
