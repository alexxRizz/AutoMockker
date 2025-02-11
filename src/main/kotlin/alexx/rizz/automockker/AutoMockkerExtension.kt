package alexx.rizz.automockker

import io.mockk.*
import org.junit.jupiter.api.extension.*
import kotlin.reflect.*

class AutoMockkerExtension : BeforeEachCallback, BeforeTestExecutionCallback {

  override fun beforeEach(context: ExtensionContext) {
    // println("AutoMockkerExtension beforeEach()")
    val needMocks = context.requiredTestInstance as INeedMockks<*>
    val mocks = AutoMockker(MockkingImpl)
    needMocks.mocks = mocks
  }

  override fun beforeTestExecution(context: ExtensionContext) {
    // println("AutoMockkerExtension beforeTestExecution()")
    val needMocks = context.requiredTestInstance as INeedMockks<*>
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

private object MockkingImpl : Mocking {

  override fun <T : Any> mockClass(type: KClass<T>): T =
    mockkClass(type, relaxed = true)
}