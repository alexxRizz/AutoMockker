package alexx.rizz.automockker

import org.junit.jupiter.api.extension.*

class AutoMockkerExtension : BeforeEachCallback, BeforeTestExecutionCallback {

  override fun beforeEach(context: ExtensionContext) {
    // println("AutoMockkerExtension beforeEach()")
    val needMocks = context.requiredTestInstance as INeedMockksWithSut<*>
    val mocks = AutoMockker()
    needMocks.mocks = mocks
  }

  override fun beforeTestExecution(context: ExtensionContext) {
    // println("AutoMockkerExtension beforeTestExecution()")
    val needMocks = context.requiredTestInstance as INeedMockksWithSut<*>
    val sut = needMocks.newSut()
    if (sut != null) {
      needMocks.setSutImpl(sut)
    } else {
      val sutClass = SutReflectionExtensions.getSutClass(needMocks)
      val autoMockedSut = needMocks.mocks.sut(sutClass)
      needMocks.setSutImpl(autoMockedSut)
    }
  }
}