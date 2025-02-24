package com.fdlr.testemb.data.mappers

import com.fdlr.testemb.data.dto.ExchangeRateDto
import com.fdlr.testemb.data.source.local.model.ExchangeRateEntity
import com.fdlr.testemb.domain.mappers.DomainMapper
import com.fdlr.testemb.domain.mappers.DtoMapper
import com.fdlr.testemb.domain.model.ExchangeRate

internal class ExchangeRateMapper : DomainMapper<ExchangeRateEntity, ExchangeRate>,
    DtoMapper<ExchangeRateDto, ExchangeRate> {
    override fun toDomain(from: ExchangeRateEntity): ExchangeRate {
        return ExchangeRate(
            time = from.timestamp,
            assetIdBase = from.assetIdBase,
            assetIdQuote = from.assetIdQuote,
            rate = from.rate,
            imageUrl = from.url
        )
    }

    override fun fromDto(from: ExchangeRateDto): ExchangeRate {
        return ExchangeRate(
            time = from.time ?: String(),
            assetIdBase = from.assetIdBase ?: String(),
            assetIdQuote = from.assetIdQuote ?: String(),
            rate = from.rate ?: 0.0,
            imageUrl = String()
        )
    }
}