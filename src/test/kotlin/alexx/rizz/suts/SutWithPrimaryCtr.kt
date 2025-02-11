package alexx.rizz.suts

class SutWithPrimaryCtr constructor(
  private val mSomeDependency: ISomeDependency,
) {

  val ctrWasCalled: Boolean

  init {
    println("SutWithPrimaryCtr: from ctr")
    ctrWasCalled = true
  }

  fun testDependency(): String {
    println(mSomeDependency.str)
    return mSomeDependency.str
  }
}