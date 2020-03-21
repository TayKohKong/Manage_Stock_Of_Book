package tay.example.manage_stock_of_book.api.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class BookDTO(
        var id: Long,
        var title: String,
        var isbn: String,
        var author: String,
        var publisher: String,
        var edition: String,
        var price: Float,
        var quantity: Int,
        var status: String,
        var publish_year: Int,
        var category: Long
)


data class CreateBookDTO(

    @field:NotNull
    var title: String,

    @field:NotNull
    @field:Pattern(regexp = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "ISBN format is invalid")
    var isbn: String,

    @field:NotNull
    var author: String,

    @field:NotNull
    var publisher: String,

    @field:NotNull
    var edition: String,

    @field:NotNull
    var price: Float,

    @field:NotNull
    var quantity: Int,

    @field:NotNull
    var status: String,

    @field:NotNull
    var publish_year: Int,

    @field:NotNull
    var category: Long
)

data class UpdateBookDTO(

        var title: String?,

        @field:Pattern(regexp = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "ISBN format is invalid")
        var isbn: String?,

        var author: String?,

        var publisher: String?,

        var edition: String?,

        var price: Float?,

        var quantity: Int?,

        var status: String?,

        var publish_year: Int?,

        var category: Long?
)