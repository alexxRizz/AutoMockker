package alexx.rizz.example

import alexx.rizz.automockker.*
import org.junit.jupiter.api.*
import kotlin.reflect.*

abstract class TestsBase : INeedMockks {

  override lateinit var mocks: AutoMockker

  @BeforeEach
  fun setUpTestsBase() {
    mocks = AutoMockker()
  }
}

abstract class SutTestsBase<TSut : Any> : TestsBase(), INeedMockksWithSut<TSut> {

  override lateinit var sut: TSut

  protected open fun beforeSutCreation() = Unit

  @BeforeEach
  fun setUpSutTestsBase() {
    beforeSutCreation()
    val newSut = newSut()
    @Suppress("UNCHECKED_CAST")
    sut =
      if (newSut != null) {
        newSut
      } else {
        val sutClass = SutReflectionExtensions.getSutClass(this) as KClass<TSut>
        mocks.sut(sutClass)
      }
  }
}