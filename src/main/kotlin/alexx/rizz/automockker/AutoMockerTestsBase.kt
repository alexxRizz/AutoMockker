package alexx.rizz.automockker

import org.junit.jupiter.api.extension.*

@ExtendWith(AutoMockkerExtension::class)
open class AutoMockerTestsBase<TSut : Any> : INeedMockks<TSut> {

  override lateinit var sut: TSut

  override lateinit var mocks: AutoMockker
}