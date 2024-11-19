package com.example.myweatherapp

import com.example.myweatherapp.presentation.city.CityIntention
import com.example.myweatherapp.presentation.city.CityState
import com.example.myweatherapp.presentation.city.CityViewModel
import com.example.myweatherapp.presentation.city.CityViewModelFactory
import com.example.myweatherapp.resources.MockRepository
import com.example.myweatherapp.resources.MockRouter
import com.example.myweatherapp.resources.RepositoryMockError
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class ExampleUnitTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    val repository = MockRepository()
    val router = MockRouter()

    val repositoryError = RepositoryMockError()

    val factory = CityViewModelFactory(repository,router)
    val viewModel = factory.create(CityViewModel::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun cityViewModel_search_cords()  = runTest(timeout = 3.seconds) {
        val result = CityState.Success(listOf(repository.cordoba))

        launch(Dispatchers.Main) {
            viewModel.execute(intention = CityIntention.Search("cor"))
            delay(1.milliseconds)
            assertEquals(result, viewModel.state)
        }
    }

    @Test
    fun cityViewModel_search_plata()  = runTest(timeout = 3.seconds) {
        val result = CityState.Success(listOf(repository.laPlata))

        launch(Dispatchers.Main) {
            viewModel.execute(intention = CityIntention.Search("plata"))
            delay(1.milliseconds)
            assertEquals(result, viewModel.state)
        }
    }

    @Test
    fun cityViewModel_search_empty()  = runTest(timeout = 3.seconds) {
        val result = CityState.Empty

        launch(Dispatchers.Main) {
            viewModel.execute(intention = CityIntention.Search("ExampleCity"))
            delay(1.milliseconds)
            assertEquals(result, viewModel.state)
        }
    }

    @Test
    fun cityViewModel_search_error()  = runTest(timeout = 3.seconds) {
        val fa = CityViewModelFactory(repositoryError,router)
        val vm = fa.create(CityViewModel::class.java)

        val result = CityState.Error("Unknown error")

        launch(Dispatchers.Main) {
            vm.execute(intention = CityIntention.Search("error"))
            delay(1.milliseconds)
            assertEquals(result, vm.state)
        }
    }
}