package com.example.myweatherapp

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
//    //Thread para simular la UI
//    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
//
//    //Dependencias mock
//    val repositorio = MockRepository()
//    val router = MockRouter()
//
//    val repositorioError = MockRepository()
//
//    //Armo ViewModel
//    val factory = CityViewModelFactory(repositorio,router)
//    val viewModel = factory.create(CityViewModel::class.java)
//
//    @Before
//    fun setUp() {
//        Dispatchers.setMain(mainThreadSurrogate)
//    }
//
//    @After
//    fun tearDown() {
//        Dispatchers.resetMain()
//        mainThreadSurrogate.close()
//    }
//
//    @Test
//    fun ciudadesViewModel_buscar_cor()  = runTest(timeout = 3.seconds) {
//        //Creo Valor esperado
//        val estadoEsperado = CiudadesEstado.Resultado(listOf(repositorio.cordoba))
//
//        launch(Dispatchers.Main) {
//            viewModel.ejecutar(intencion = CiudadesIntencion.Buscar("cor"))
//            delay(1.milliseconds)
//            assertEquals(estadoEsperado, viewModel.uiState)
//        }
//    }
//
//    @Test
//    fun ciudadesViewModel_buscar_plata()  = runTest(timeout = 3.seconds) {
//        //Creo Valor esperado
//        val estadoEsperado = CiudadesEstado.Resultado(listOf(repositorio.laPlata))
//
//        launch(Dispatchers.Main) {
//            viewModel.ejecutar(intencion = CiudadesIntencion.Buscar("plata"))
//            delay(1.milliseconds)
//            assertEquals(estadoEsperado, viewModel.uiState)
//        }
//    }
//
//    @Test
//    fun ciudadesViewModel_buscar_vacio()  = runTest(timeout = 3.seconds) {
//        //Creo Valor esperado
//        val estadoEsperado = CiudadesEstado.Vacio
//
//        launch(Dispatchers.Main) {
//            viewModel.ejecutar(intencion = CiudadesIntencion.Buscar("jojo"))
//            delay(1.milliseconds)
//            assertEquals(estadoEsperado, viewModel.uiState)
//        }
//    }
//
//    @Test
//    fun ciudadesViewModel_buscar_error()  = runTest(timeout = 3.seconds) {
//
//        val repositorioError = RepositorioMockError()
//
//        //Armo ViewModel
//        val fa = CiudadesViewModelFactory(repositorioError,router)
//        val vm = fa.create(CiudadesViewModel::class.java)
//
//        //Creo Valor esperado
//        val estadoEsperado = CiudadesEstado.Error("error desconocido")
//
//        launch(Dispatchers.Main) {
//            vm.ejecutar(intencion = CiudadesIntencion.Buscar("jojo"))
//            delay(1.milliseconds)
//            assertEquals(estadoEsperado, vm.uiState)
//        }
//    }
}