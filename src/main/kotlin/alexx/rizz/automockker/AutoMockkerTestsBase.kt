package alexx.rizz.automockker

import org.junit.jupiter.api.extension.*

@ExtendWith(AutoMockkerExtension::class)
open class AutoMockkerTestsBase<TSut : Any> : INeedMockksWithSut<TSut> {

  override lateinit var sut: TSut

  override lateinit var mocks: AutoMockker
}