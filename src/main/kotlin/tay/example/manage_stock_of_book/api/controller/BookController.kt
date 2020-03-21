package tay.example.manage_stock_of_book.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tay.example.manage_stock_of_book.api.request.CreateBookDTO
import tay.example.manage_stock_of_book.api.request.UpdateBookDTO
import tay.example.manage_stock_of_book.service.BookService
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/books")
class BookController(
        @Autowired
        var bookService: BookService
){

    @GetMapping
    fun getListBook(@RequestParam(value = "value", required = false, defaultValue = "null") value: String,
                    @RequestParam(value = "categoryId", required = false, defaultValue = "0") categoryId: Long,
                    @RequestParam(value = "page", required = false, defaultValue = "0") page: Int,
                    @RequestParam(value = "limit", required = false, defaultValue = "0") limit: Int
    ): ResponseEntity<Any>{
        return bookService.getListBook(value,categoryId.toLong(),page.toInt(),limit.toInt())
    }

    @PostMapping
    fun createBook(@Valid @RequestBody createBookDTO: CreateBookDTO): ResponseEntity<Any>{
        return ResponseEntity.ok(bookService.createBook(createBookDTO))
    }

    @GetMapping("/{id}")
    fun getDetailBook(@PathVariable id: Long): ResponseEntity<Any>{
        return ResponseEntity.ok(bookService.getDetailBook(id))
    }

    @PutMapping("/{id}")
    fun updateBook(@Valid @RequestBody updateBookDTO: UpdateBookDTO, @PathVariable id: Long): ResponseEntity<Any>{
        return ResponseEntity.ok(bookService.updateBook(updateBookDTO,id))
    }
}