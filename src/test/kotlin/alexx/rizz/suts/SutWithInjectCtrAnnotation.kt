package alexx.rizz.suts

import javax.inject.*

class SutWithInjectCtrAnnotation @Inject constructor(
  private val mSomeDependency: ISomeDependency,
) {

  val ctrWasCalled: Boolean

  init {
    println("SutWithInjectCtrAnnotation: from ctr")
    ctrWasCalled = true
  }

  fun testDependency(): String {
    println(mSomeDependency.str)
    return mSomeDependency.str
  }
}