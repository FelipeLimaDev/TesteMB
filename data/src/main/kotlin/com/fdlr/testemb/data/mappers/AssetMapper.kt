package com.fdlr.testemb.data.mappers

import com.fdlr.testemb.data.dto.AssetDto
import com.fdlr.testemb.domain.mappers.DomainMapper
import com.fdlr.testemb.domain.model.Asset

internal class AssetMapper : DomainMapper<AssetDto, Asset>{
    override fun toDomain(from: AssetDto): Asset {
        return Asset(
            assetId = from.assetId ?: String(),
            name = from.name ?: String(),
            isCrypto = from.isCrypto ?: 0
        )
    }
}
