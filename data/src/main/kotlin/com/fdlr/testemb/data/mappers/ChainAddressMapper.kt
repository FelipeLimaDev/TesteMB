package com.fdlr.testemb.data.mappers

import com.fdlr.testemb.data.dto.ChainAddressDto
import com.fdlr.testemb.domain.mappers.DomainMapper
import com.fdlr.testemb.domain.model.ChainAddress

internal class ChainAddressMapper : DomainMapper<ChainAddressDto, ChainAddress> {
    override fun toDomain(from: ChainAddressDto): ChainAddress {
        return ChainAddress(
            address = from.address ?: String(),
            chainId = from.chainId ?: String(),
            networkId = from.networkId ?: String()
        )
    }
}

