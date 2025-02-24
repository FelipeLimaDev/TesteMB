package com.fdlr.testemb.coin.domain.usecase

import com.fdlr.testemb.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetAssetIconsUseCaseTest {

    private val repository: CoinRepository = mockk()
    private lateinit var useCase: GetAssetIconsUseCase

    @Before
    fun setUp() {
        useCase = GetAssetIconsUseCase(repository)
    }

    @Test
    fun `invoke - sucesso`() = runBlocking {
        coEvery { repository.getAssetIcons() } returns Unit

        useCase()
    }

    @Test
    fun `invoke - repositorio lanca excecao`() = runBlocking {
        coEvery { repository.getAssetIcons() } throws RuntimeException("Erro inesperado")

        try {
            useCase()
        } catch (e: Exception) {
            assertTrue(e is RuntimeException)
            assertEquals("Erro inesperado", e.message)
        }
    }
}
