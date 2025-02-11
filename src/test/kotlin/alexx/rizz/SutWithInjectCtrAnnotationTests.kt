package alexx.rizz

import alexx.rizz.automockker.*
import alexx.rizz.suts.*
import io.kotest.matchers.*
import io.mockk.*
import org.junit.jupiter.api.*

class SutWithInjectCtrAnnotationTests : AutoMockerTestsBase<SutWithInjectCtrAnnotation>() {

  private val mSomeDependency get() = mocks.mock<ISomeDependency>()

  @BeforeEach
  fun setUp() {
    // println("Tests BeforeEach")
  }

  @Test
  fun `Should call ctr with inject annotation`() {
    sut.ctrWasCalled shouldBe true
  }

  @Test
  fun `Should get injected mock`() {
    every { mSomeDependency.str } returns "test"

    sut.testDependency() shouldBe "test"
  }
}