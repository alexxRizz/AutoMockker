package alexx.rizz.automockker

interface INeedMockks {

  var mocks: AutoMockker
}

interface INeedMockksWithSut<TSut : Any> : INeedMockks {

  var sut: TSut

  fun newSut(): TSut? = null

  fun setSutImpl(sut: Any) {
    @Suppress("UNCHECKED_CAST")
    this.sut = sut as TSut
  }
}