package com.pgf.mediamonks.core.interactor

import com.pgf.mediamonks.core.exception.Failure
import com.pgf.mediamonks.core.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out RESULT_TYPE, in REQUEST_PARAMS> where RESULT_TYPE : Any {

    abstract suspend fun run(params: REQUEST_PARAMS): Either<Failure, RESULT_TYPE>

    operator fun invoke(params: REQUEST_PARAMS, onResult: (Either<Failure, RESULT_TYPE>) -> Unit = {}) {
        GlobalScope.launch(Dispatchers.Main) {
            val job = async(Dispatchers.IO) { run(params)}
            onResult(job.await())
        }
    }

}