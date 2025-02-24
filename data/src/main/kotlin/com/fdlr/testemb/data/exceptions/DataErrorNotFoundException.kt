package com.fdlr.testemb.data.exceptions

class DataErrorNotFoundException(
    message: String = "Data not found",
    cause: Throwable? = null
) : Exception(message, cause)
