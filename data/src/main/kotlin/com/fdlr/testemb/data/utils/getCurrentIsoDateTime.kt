package com.fdlr.testemb.data.utils

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun getCurrentIsoDateTime(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
        .withZone(ZoneOffset.UTC)

    return formatter.format(Instant.now())
}