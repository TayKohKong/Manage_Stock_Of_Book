package tay.example.manage_stock_of_book.api.exception

import java.util.*

data class ErrorResponse(val timestamp : Date, val code: Int, val errorMessage: String?) {
    constructor(errorCode : ErrorCode, errorField : String?) : this(Date(), errorCode.code, String.format(errorCode.message, errorField))
}
