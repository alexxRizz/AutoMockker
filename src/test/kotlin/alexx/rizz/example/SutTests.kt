package alexx.rizz.example

import alexx.rizz.suts.*
import io.kotest.matchers.*
import io.mockk.*
import org.junit.jupiter.api.*

class SutTests : SutTestsBase<SutWithPrimaryCtr>() {

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