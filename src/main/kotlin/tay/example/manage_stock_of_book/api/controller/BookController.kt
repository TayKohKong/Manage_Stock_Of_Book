package tay.example.manage_stock_of_book.api.controller

import org.hibernate.dialect.Ingres10Dialect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tay.example.manage_stock_of_book.api.request.CreateBookDTO
import tay.example.manage_stock_of_book.api.request.UpdateBookDTO
import tay.example.manage_stock_of_book.service.BookService
import javax.validation.Valid


@RestController
@RequestMapping("/books")
class BookController(
        @Autowired
        var bookService: BookService
){

    @GetMapping
    fun getListBook(@RequestParam(value = "q", defaultValue = "") q: String?,
                    @RequestParam(value = "categoryId", required = false, defaultValue = "0") categoryId: Long?,
                    @RequestParam(value = "page",defaultValue = "5",required = false) page: Int,
                    @RequestParam(value = "limit", defaultValue = "0", required = false) limit: Int
    ): ResponseEntity<Any>{
        return ResponseEntity.ok(bookService.getListBook(q,categoryId,page.toInt(),limit.toInt()))
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