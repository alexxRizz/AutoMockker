package alexx.rizz.suts

import javax.inject.*

class SutWithInjectCtrAnnotation2 @Inject constructor(
  private val mSomeDependency: ISomeDependency,
) {

  constructor(someDependency: ISomeDependency, someValue: String) : this(someDependency)

  val ctrWasCalled: Boolean

  init {
    println("SutWithInjectCtrAnnotation2: from ctr")
    ctrWasCalled = true
  }

  fun testDependency(): String {
    println(mSomeDependency.str)
    return mSomeDependency.str
  }
}