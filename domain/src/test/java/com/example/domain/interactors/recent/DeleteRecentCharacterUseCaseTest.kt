package com.example.domain.interactors.recent

import com.example.domain.interactors.testutils.TestExecutionThreadImpl
import com.example.domain.repository.StarWarsDataRepository
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class DeleteRecentCharacterUseCaseTest {

    private val executionThread = TestExecutionThreadImpl()

    @MockK
    lateinit var local: StarWarsDataRepository

    private lateinit var deleteRecentCharacterUseCase: DeleteRecentCharacterUseCase

    companion object {
        const val CHARACTER_NAME = "character_name"
    }


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        deleteRecentCharacterUseCase = DeleteRecentCharacterUseCase(executionThread, local)
    }


    @Test
    fun `verify that when invoked deleteCharacter is called`() = runBlockingTest {
        stubResponse()
        deleteRecentCharacterUseCase.invoke(CHARACTER_NAME)
        coVerify(exactly = 1) { local.deleteCharacter(any()) }
    }


    @Test(expected = IllegalStateException::class)
    fun `verify that when invoked with a null params an error is thrown`() = runBlockingTest {
        stubResponse()
        deleteRecentCharacterUseCase.invoke(null)
    }


    @Test
    fun `verify that deleteCharacter is called with the correct params`() = runBlockingTest {
        stubResponse()
        deleteRecentCharacterUseCase.invoke(CHARACTER_NAME)
        val slot = CapturingSlot<String>()
        coVerify (exactly = 1) { local.deleteCharacter(capture(slot)) }

        Truth.assertThat(slot.captured)
            .isEqualTo(CHARACTER_NAME)
    }

    private fun stubResponse() {
        coEvery { local.deleteCharacter(any()) } just runs
    }
}