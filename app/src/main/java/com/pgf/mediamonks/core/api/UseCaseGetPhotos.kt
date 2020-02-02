package com.pgf.mediamonks.core.api

import com.pgf.mediamonks.core.api.model.PhotoItem
import com.pgf.mediamonks.core.exception.Failure
import com.pgf.mediamonks.core.functional.Either
import com.pgf.mediamonks.core.interactor.UseCase
import javax.inject.Inject

class UseCaseGetPhotos
@Inject constructor(private val photosRepository: PhotosRepository) : UseCase<List<PhotoItem>, UseCaseGetPhotos.Params>() {

    override suspend fun run(params: Params): Either<Failure, List<PhotoItem>> =
        photosRepository.getPhotos(params)

    data class Params(val albumId: Int)
}
