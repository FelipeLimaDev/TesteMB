package com.fdlr.testemb.data.mappers

import com.fdlr.testemb.data.dto.CoinDto
import com.fdlr.testemb.data.source.local.model.ExchangeRateEntity
import com.fdlr.testemb.domain.mappers.DomainMapper
import com.fdlr.testemb.domain.mappers.EntityMapper
import com.fdlr.testemb.domain.model.Coin
import com.fdlr.testemb.domain.model.Rate

internal class CoinMapper : DomainMapper<List<ExchangeRateEntity>, Coin>, EntityMapper<CoinDto,List<ExchangeRateEntity> > {
    override fun toDomain(from: List<ExchangeRateEntity>): Coin {
        return Coin(
            assetIdBase = from[0].assetIdBase.removePrefix("$"),
            rates = from.map {
                Rate(
                    id = it.id,
                    assetIdQuote = it.assetIdQuote.removePrefix("$"),
                    time = it.timestamp,
                    rate = it.rate,
                    imageUrl = it.url
                )
            }
        )
    }

    override fun toEntity(from: CoinDto): List<ExchangeRateEntity> {
        return from.rates?.mapNotNull { rateDto ->
            if (rateDto?.assetIdQuote != null && rateDto.rate != null && from.assetIdBase != null && rateDto.time != null) {
                ExchangeRateEntity(
                    assetIdQuote = rateDto.assetIdQuote.removePrefix("$"),
                    rate = rateDto.rate,
                    timestamp = rateDto.time,
                    assetIdBase = from.assetIdBase.removePrefix("$")
                )
            } else {
                null
            }
        } ?: emptyList()
    }
}

