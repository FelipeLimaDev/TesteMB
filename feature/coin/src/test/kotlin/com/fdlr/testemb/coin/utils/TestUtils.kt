package com.fdlr.testemb.coin.utils

import com.fdlr.testemb.domain.model.*

object TestUtils {

    // --- Domain Models Mocks ---
    val sampleChainAddress = ChainAddress(
        address = "0x123456789",
        chainId = "eth-mainnet",
        networkId = "1"
    )

    val sampleAssetDetails = AssetDetails(
        assetId = "BTC",
        chainAddresses = listOf(sampleChainAddress),
        dataEnd = "2025-12-31T23:59:59",
        dataOrderbookEnd = "2025-12-31T23:59:59",
        dataOrderbookStart = "2022-01-01T00:00:00",
        dataQuoteEnd = "2025-12-31T23:59:59",
        dataQuoteStart = "2022-01-01T00:00:00",
        dataStart = "2022-01-01T00:00:00",
        dataSymbolsCount = 1500,
        dataTradeEnd = "2025-12-31T23:59:59",
        dataTradeStart = "2022-01-01T00:00:00",
        assetName = "Bitcoin",
        priceUsd = 27000.0,
        typeIsCrypto = 1,
        volume1DayUsd = 500000000.0,
        volume1HrsUsd = 20000000.0,
        volume1MthUsd = 15000000000.0
    )

    val sampleAsset = Asset(
        assetId = "BTC",
        name = "Bitcoin",
        isCrypto = 1
    )

    val sampleRate = Rate(
        id = 123,
        assetIdQuote = "ETH",
        rate = 0.07,
        time = "2025-01-01T12:00:00",
        imageUrl = "https://example.com/eth.png"
    )

    val sampleExchangeRate = ExchangeRate(
        time = "2025-01-01T12:00:00",
        assetIdBase = "BTC",
        assetIdQuote = "ETH",
        rate = 0.07,
        imageUrl = "https://example.com/eth.png"
    )

    val sampleCoin = Coin(
        assetIdBase = "BTC",
        rates = listOf(sampleRate)
    )
}
