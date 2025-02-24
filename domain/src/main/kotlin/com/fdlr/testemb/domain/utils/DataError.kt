package com.fdlr.testemb.domain.utils

sealed interface DataError: Error {
    enum class Remote : DataError {
        REQUEST_TIMEOUT,        // Requisição demorou muito tempo e foi interrompida
        NO_INTERNET,            // Sem conexão com a internet
        CONNECTION_FAILED,      // Erro ao tentar conectar ao servidor
        BAD_REQUEST,            // Erro 400 - Requisição mal formada
        UNAUTHORIZED,           // Erro 401 - Não autorizado
        FORBIDDEN,              // Erro 403 - Acesso proibido
        NOT_FOUND,              // Erro 404 - Recurso não encontrado
        CLIENT_ERROR,           // Erro 4xx - Erro do cliente
        INTERNAL_SERVER_ERROR,  // Erro 500 - Erro interno do servidor
        HTTP_ERROR,             // Qualquer outro erro HTTP
        SSL_ERROR,              // Erro de SSL - Problema no handshake
        SSL_EXCEPTION,          // Erro genérico de SSL
        OUT_OF_MEMORY,          // Falta de memória ao processar resposta
        QUOTA_ERROR,            // Acabou a cota de requisições
        UNKNOWN                 // Erro desconhecido
    }


    enum class Local: DataError {
        CONSTRAINT_VIOLATION,   // Violação de constraint no banco de dados
        GENERIC_ERROR,          // Erro genérico no banco de dados
        OUT_OF_MEMORY,          // Falta de memória ao processar resposta
        UNKNOWN                 // Erro desconhecido
    }
}