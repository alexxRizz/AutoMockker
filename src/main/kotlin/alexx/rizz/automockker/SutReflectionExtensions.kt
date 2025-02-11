package alexx.rizz.automockker

import kotlin.reflect.*
import kotlin.reflect.full.*

object SutReflectionExtensions {

  fun getSutClass(tests: INeedMockksWithSut<*>): KClass<*> {
    val needMocks = tests::class.allSupertypes.first { it.classifier == INeedMockksWithSut::class }
    val type = needMocks.arguments[0].type!!.classifier
    return type as KClass<*>
  }
}