package com.fdlr.testemb.coin.presentation.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.fdlr.testemb.coin.R
import com.fdlr.testemb.coreds.components.dialog.DialogType
import com.fdlr.testemb.coreds.components.dialog.MBDialog
import com.fdlr.testemb.domain.utils.DataError

@Composable
fun ErrorScreen(
    error: DataError,
    customTitle: String? = null,
    customMessage: String? = null,
    customDialogType: DialogType? = null,
    retryButtonText: String = stringResource(R.string.retry_button_text),
    onExitApp: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    val (dialogType, titleRes) = when (error) {
        DataError.Remote.REQUEST_TIMEOUT -> DialogType.WARNING to R.string.error_title_request_timeout
        DataError.Remote.NO_INTERNET -> DialogType.ERROR to R.string.error_title_no_internet
        DataError.Remote.CONNECTION_FAILED -> DialogType.ERROR to R.string.error_title_connection_failed
        DataError.Remote.BAD_REQUEST -> DialogType.WARNING to R.string.error_title_bad_request
        DataError.Remote.UNAUTHORIZED -> DialogType.ERROR to R.string.error_title_unauthorized
        DataError.Remote.FORBIDDEN -> DialogType.ERROR to R.string.error_title_forbidden
        DataError.Remote.NOT_FOUND -> DialogType.WARNING to R.string.error_title_not_found
        DataError.Remote.CLIENT_ERROR -> DialogType.WARNING to R.string.error_title_client_error
        DataError.Remote.INTERNAL_SERVER_ERROR -> DialogType.ERROR to R.string.error_title_internal_server_error
        DataError.Remote.HTTP_ERROR -> DialogType.WARNING to R.string.error_title_http_error
        DataError.Remote.SSL_ERROR, DataError.Remote.SSL_EXCEPTION -> DialogType.WARNING to R.string.error_title_ssl_error
        DataError.Remote.OUT_OF_MEMORY -> DialogType.ERROR to R.string.error_title_out_of_memory
        DataError.Remote.QUOTA_ERROR -> DialogType.WARNING to R.string.error_title_quota_error
        DataError.Remote.UNKNOWN -> DialogType.ERROR to R.string.error_title_unknown
        DataError.Local.CONSTRAINT_VIOLATION -> DialogType.ERROR to R.string.error_title_constraint_violation
        DataError.Local.GENERIC_ERROR -> DialogType.ERROR to R.string.error_title_generic_error
        DataError.Local.OUT_OF_MEMORY -> DialogType.ERROR to R.string.error_title_out_of_memory
        DataError.Local.UNKNOWN -> DialogType.ERROR to R.string.error_title_unknown
    }

    val title = customTitle ?: stringResource(titleRes)

    val messageRes = when (error) {
        DataError.Remote.REQUEST_TIMEOUT -> R.string.error_message_request_timeout
        DataError.Remote.NO_INTERNET -> R.string.error_message_no_internet
        DataError.Remote.CONNECTION_FAILED -> R.string.error_message_connection_failed
        DataError.Remote.BAD_REQUEST -> R.string.error_message_bad_request
        DataError.Remote.UNAUTHORIZED -> R.string.error_message_unauthorized
        DataError.Remote.FORBIDDEN -> R.string.error_message_forbidden
        DataError.Remote.NOT_FOUND -> R.string.error_message_not_found
        DataError.Remote.CLIENT_ERROR -> R.string.error_message_client_error
        DataError.Remote.INTERNAL_SERVER_ERROR -> R.string.error_message_internal_server_error
        DataError.Remote.HTTP_ERROR -> R.string.error_message_http_error
        DataError.Remote.SSL_ERROR, DataError.Remote.SSL_EXCEPTION -> R.string.error_message_ssl_error
        DataError.Remote.OUT_OF_MEMORY -> R.string.error_message_out_of_memory
        DataError.Remote.QUOTA_ERROR -> R.string.error_message_quota_error
        DataError.Remote.UNKNOWN -> R.string.error_message_unknown
        DataError.Local.CONSTRAINT_VIOLATION -> R.string.error_message_constraint_violation
        DataError.Local.GENERIC_ERROR -> R.string.error_message_generic_error
        DataError.Local.OUT_OF_MEMORY -> R.string.error_message_out_of_memory
        DataError.Local.UNKNOWN -> R.string.error_message_unknown
    }

    val defaultErrorMessage = customMessage ?: stringResource(messageRes)

    MBDialog(
        type = customDialogType ?: dialogType,
        title = title,
        description = defaultErrorMessage,
        buttonText = retryButtonText,
        onExitApp = onExitApp,
        onDismiss = onDismiss
    )
}