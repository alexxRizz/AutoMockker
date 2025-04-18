package alexx.rizz

import alexx.rizz.automockker.*
import alexx.rizz.suts.*
import io.kotest.matchers.*
import io.mockk.*
import org.junit.jupiter.api.*

class SutWithPrimaryCtrTests : AutoMockkerTestsBase<SutWithPrimaryCtr>() {

  private val mSomeDependency get() = mocks.mock<ISomeDependency>()

  @BeforeEach
  fun setUp() {
    // println("Tests BeforeEach")
  }

  @Test
  fun `Should call primary ctr`() {
    sut.ctrWasCalled shouldBe true
  }

  @Test
  fun `Should use injected mock`() {
    every { mSomeDependency.str } returns "test"

    sut.testDependency() shouldBe "test"
  }
}