package alexx.rizz.automockker

import io.mockk.*

inline fun <reified T : Any> mockkRelaxed() =
  mockk<T>(relaxed = true)

fun verifyOnce(verifyBlock: MockKVerificationScope.() -> Unit) =
  verify(exactly = 1, verifyBlock = verifyBlock)

fun verifyOnceWithOrder(verifyBlock: MockKVerificationScope.() -> Unit) {
  verifyOnce(verifyBlock = verifyBlock)
  verifyOrder(verifyBlock = verifyBlock)
}

fun verifyNever(verifyBlock: MockKVerificationScope.() -> Unit
) =
  verify(exactly = 0, verifyBlock = verifyBlock)

fun coVerifyOnce(verifyBlock: suspend MockKVerificationScope.() -> Unit) =
  coVerify(exactly = 1, verifyBlock = verifyBlock)

fun coVerifyOnceWithOrder(verifyBlock: suspend MockKVerificationScope.() -> Unit) {
  coVerifyOnce(verifyBlock = verifyBlock)
  coVerifyOrder(verifyBlock = verifyBlock)
}

fun coVerifyNever(verifyBlock: suspend MockKVerificationScope.() -> Unit) =
  coVerify(exactly = 0, verifyBlock = verifyBlock)

fun verifyWasNotCalled(mock: Any) =
  verify { mock wasNot called }

fun verifyWasNotCalled(vararg mocks: Any) =
  mocks.forEach {
    verify { it wasNot called }
  }

fun <T> coEveryThrows(stubBlock: suspend MockKMatcherScope.() -> T): Exception {
  val ex = RuntimeException("test")
  coEvery(stubBlock) throws ex
  return ex
}

fun <T> everyThrows(stubBlock: MockKMatcherScope.() -> T): Exception {
  val ex = RuntimeException("test")
  every(stubBlock) throws ex
  return ex
}

fun resetMock(mock: Any, vararg mocks: Any) =
  clearMocks(mock, *mocks, answers = false, exclusionRules = false)

fun resetAllMocks() =
  clearAllMocks(answers = false)
