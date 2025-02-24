package com.fdlr.testemb.data.mappers

import com.fdlr.testemb.data.dto.AssetDetailsDto
import com.fdlr.testemb.domain.mappers.DomainMapper
import com.fdlr.testemb.domain.model.AssetDetails

class AssetDetailsMapper : DomainMapper<AssetDetailsDto, AssetDetails> {
    override fun toDomain(from: AssetDetailsDto): AssetDetails {
        return AssetDetails(
            assetId = from.assetId ?: String(),
            chainAddresses = from.chainAddresses?.map { ChainAddressMapper().toDomain(it) }
                ?: emptyList(),
            dataEnd = from.dataEnd ?: String(),
            dataOrderbookEnd = from.dataOrderbookEnd ?: String(),
            dataOrderbookStart = from.dataOrderbookStart ?: String(),
            dataQuoteEnd = from.dataQuoteEnd ?: String(),
            dataQuoteStart = from.dataQuoteStart ?: String(),
            dataStart = from.dataStart ?: String(),
            dataSymbolsCount = from.dataSymbolsCount ?: 0,
            dataTradeEnd = from.dataTradeEnd ?: String(),
            dataTradeStart = from.dataTradeStart ?: String(),
            assetName = from.assetName ?: String(),
            priceUsd = from.priceUsd ?: 0.0,
            typeIsCrypto = from.typeIsCrypto ?: 0,
            volume1DayUsd = from.volume1DayUsd ?: 0.0,
            volume1HrsUsd = from.volume1HrsUsd ?: 0.0,
            volume1MthUsd = from.volume1MthUsd ?: 0.0
        )
    }
}