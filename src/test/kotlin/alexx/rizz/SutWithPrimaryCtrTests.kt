package alexx.rizz

import alexx.rizz.automockker.*
import alexx.rizz.suts.*
import io.kotest.matchers.*
import io.mockk.*
import org.junit.jupiter.api.*

class SutWithPrimaryCtrTests : AutoMockerTestsBase<SutWithPrimaryCtr>() {

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
  fun `Should get injected mock`() {
    every { mSomeDependency.str } returns "test"

    sut.testDependency() shouldBe "test"
  }
}