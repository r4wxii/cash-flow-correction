package com.r4wxii.cashflowcorrection.domain.usecase

import com.r4wxii.cashflowcorrection.domain.model.repository.AccountRepository
import com.r4wxii.cashflowcorrection.domain.model.usecase.AccountUseCase
import javax.inject.Inject

class AccountUseCaseImpl @Inject constructor(
    private val repository: AccountRepository
): AccountUseCase {

}