package tay.example.manage_stock_of_book.api.exception

import java.lang.RuntimeException

class IDNotFoundException(errorField : String) : RuntimeException(errorField) {

}