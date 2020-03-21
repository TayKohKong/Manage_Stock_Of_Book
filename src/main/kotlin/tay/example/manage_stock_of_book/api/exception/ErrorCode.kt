package tay.example.manage_stock_of_book.api.exception

enum class ErrorCode(val code: Int, val message: String) {
    URL_NOT_FOUND(1, "URL NOT FOUND"),
    INVALID_INPUT_FORMAT(2, "It is in invalid format: %s"),
    INVALID_DATA_TYPE(3, "Invalid Data Type"),
    ID_NOT_FOUND(4, "ID Not found: %s"),
    INTERNAL_SERVER_ERROR(5, "INTERNAL SERVER ERROR"),
    METHOD_NOT_ALLOW(6,"Method Not Allowed for this route")
}