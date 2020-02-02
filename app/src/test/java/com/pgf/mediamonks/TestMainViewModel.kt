package com.pgf.mediamonks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.pgf.mediamonks.core.api.UseCaseGetPhotos
import com.pgf.mediamonks.ui.main.PhotoListFragment
import com.pgf.mediamonks.ui.main.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TestMainViewModel {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private val mockUseCaseGetPhotos: UseCaseGetPhotos = Mockito.mock(UseCaseGetPhotos::class.java)

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mainViewModel = MainViewModel(mockUseCaseGetPhotos)
    }

    @Test
    fun testGetPhotosTriggersUseCase() {

        mainViewModel.getPhotos(PhotoListFragment.DEMO_ALBUM_ID)

        verify(mockUseCaseGetPhotos).invoke(any(), any())
        verifyNoMoreInteractions(mockUseCaseGetPhotos)
    }

    @Test
    fun testGetPhotosInvalidAlbumId() {

        mainViewModel.getPhotos(-1)

        verifyZeroInteractions(mockUseCaseGetPhotos)
    }
}