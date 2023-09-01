package com.base.registerform.domain.usecase.validation

import com.base.registerform.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostValidationUseCase @Inject constructor(

) {

    suspend operator fun invoke(email: String, password: String): Flow<Result<String>> =
        flow {
            emit(Result.Loading)

            emit(Result.Success(email))
        }.catch { e ->
            e.printStackTrace()
            emit(Result.Failure(Exception(e)))
        }.flowOn(Dispatchers.IO)
}