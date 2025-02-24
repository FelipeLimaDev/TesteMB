package com.fdlr.testemb.data.mappers

import com.fdlr.testemb.data.dto.AssetIconDto
import com.fdlr.testemb.data.source.local.model.AssetEntity
import com.fdlr.testemb.domain.mappers.DomainMapper
import com.fdlr.testemb.domain.mappers.EntityMapper
import com.fdlr.testemb.domain.model.AssetIcon

internal class AssetIconMapper : DomainMapper<AssetEntity, AssetIcon>,
    EntityMapper<AssetIconDto, AssetEntity> {
    override fun toDomain(from: AssetEntity): AssetIcon {
        return AssetIcon(
            assetId = from.assetId,
            url = from.imageUrl
        )
    }

    override fun toEntity(from: AssetIconDto): AssetEntity {
        return AssetEntity(
            assetId = from.assetId ?: String(),
            imageUrl = from.url ?: String()
        )
    }

}