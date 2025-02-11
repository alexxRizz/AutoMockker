package alexx.rizz.automockker

interface INeedMockks<TSut : Any> {

  var sut: TSut
  var mocks: AutoMockker

  fun newSut(): TSut? = null

  fun setSutImpl(sut: Any) {
    @Suppress("UNCHECKED_CAST")
    this.sut = sut as TSut
  }
}