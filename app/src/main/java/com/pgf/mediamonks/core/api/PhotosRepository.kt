package com.pgf.mediamonks.core.api

import com.pgf.mediamonks.core.api.model.PhotoItem
import com.pgf.mediamonks.core.exception.Failure
import com.pgf.mediamonks.core.functional.Either
import com.pgf.mediamonks.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface PhotosRepository {

    fun getPhotos(params: UseCaseGetPhotos.Params): Either<Failure, List<PhotoItem>>

    class DataSourceNetwork
    @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: RetrofitApiService
    ) : PhotosRepository {

        override fun getPhotos(params: UseCaseGetPhotos.Params): Either<Failure, List<PhotoItem>> {
            return when (networkHandler.isConnected) {
                true -> request(
                    service.getPhotos(params.albumId),
                    { responsePhotos -> responsePhotos },
                    emptyList()
                )
                false, null -> Either.Left(Failure.NetworkConnection)
            }

        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}