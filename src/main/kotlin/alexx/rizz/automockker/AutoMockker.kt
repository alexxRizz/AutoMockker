package alexx.rizz.automockker

import io.mockk.*
import javax.inject.*
import kotlin.reflect.*
import kotlin.reflect.full.*

interface Mocking {
  fun <T : Any> mockClass(type: KClass<T>): T
}

private object MockkingImpl : Mocking {

  override fun <T : Any> mockClass(type: KClass<T>): T =
    mockkClass(type, relaxed = true)
}

class AutoMockker(private val mMocking: Mocking = MockkingImpl) {

  private val mMocks = mutableMapOf<KClass<*>, Any>()
  private val mSubSuts = mutableMapOf<KClass<*>, Any>()

  @PublishedApi
  internal val mPredefined = mutableMapOf<KClass<*>, Any>()

  inline fun <reified TInterface> use(predefined: Any) {
    mPredefined[TInterface::class] = predefined
  }

  inline fun <reified T : Any> mock(): T =
    mock(T::class)

  @Suppress("UNCHECKED_CAST")
  fun <T : Any> mock(klass: KClass<T>): T {
    val predefined = mPredefined[klass]
    if (predefined != null)
      return predefined as T
    val mock = mMocks.getOrPut(klass) { mMocking.mockClass(klass) }
    return mock as T
  }

  inline fun <reified T : Any> sut(): T =
    sut(T::class)

  fun <T : Any> sut(klass: KClass<T>): T {
    val ctr = klass.constructors.firstOrNull { it.hasAnnotation<Inject>() }
      ?: klass.primaryConstructor
      ?: throw RuntimeException("No ctr found")
    val ps = ctr.parameters.map {
      val argClass = it.type.classifier as KClass<*>
      val predefined = mPredefined[argClass]
      @Suppress("IfThenToElvis")
      if (predefined != null) {
        predefined
      } else if (argClass.java.isInterface || argClass.isAbstract) {
        mock(it.type.classifier as KClass<*>)
      } else {
        val alreadyCreatedSubSut = mSubSuts[argClass]
        if (alreadyCreatedSubSut != null) {
          alreadyCreatedSubSut
        } else {
          val subSut = sut(argClass)
          mSubSuts[argClass] = subSut
          subSut
        }
      }
    }.toTypedArray()
    return ctr.call(*ps)
  }

  inline fun <reified T : Any> subSut(): T =
    subSut(T::class)

  fun <T : Any> subSut(kclass: KClass<T>): T {
    val subSut = mSubSuts.getOrPut(kclass) { sut(kclass) }
    @Suppress("UNCHECKED_CAST")
    return subSut as T
  }
}