package com.fdlr.testemb.data.utils

import android.database.sqlite.SQLiteConstraintException
import com.fdlr.testemb.data.exceptions.DataErrorNotFoundException
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.sql.SQLException
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeCall(apiCall: suspend () -> T): Result<T, DataError> {
    return try {
        Result.Success(apiCall())
    } catch (e: UnknownHostException) {
        Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: SocketTimeoutException) {
        Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: HttpException) {
        Result.Error(
            when (e.code()) {
                in 400..499 -> when (e.code()) {
                    400 -> DataError.Remote.BAD_REQUEST
                    401 -> DataError.Remote.UNAUTHORIZED
                    403 -> DataError.Remote.FORBIDDEN
                    404 -> DataError.Remote.NOT_FOUND
                    429 -> DataError.Remote.QUOTA_ERROR
                    else -> DataError.Remote.CLIENT_ERROR
                }

                in 500..599 -> DataError.Remote.INTERNAL_SERVER_ERROR
                else -> DataError.Remote.HTTP_ERROR
            }
        )
    } catch (e: OutOfMemoryError) {
        Result.Error(DataError.Remote.OUT_OF_MEMORY)
    } catch (e: SSLHandshakeException) {
        Result.Error(DataError.Remote.SSL_ERROR)
    } catch (e: SSLException) {
        Result.Error(DataError.Remote.SSL_EXCEPTION)
    } catch (e: ConnectException) {
        Result.Error(DataError.Remote.CONNECTION_FAILED)
    } catch (e: CancellationException) {
        throw e
    } catch (e: SQLiteConstraintException) {
        Result.Error(DataError.Local.CONSTRAINT_VIOLATION)
    } catch (e: OutOfMemoryError) {
        Result.Error(DataError.Local.OUT_OF_MEMORY)
    } catch (e: SQLException) {
        Result.Error(DataError.Local.GENERIC_ERROR)
    } catch (e: DataErrorNotFoundException) {
        Result.Error(DataError.Local.UNKNOWN)
    } catch (e: Exception) {
        Result.Error(DataError.Remote.UNKNOWN)
    }
}



