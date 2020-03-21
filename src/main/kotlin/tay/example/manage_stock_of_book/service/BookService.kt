package tay.example.manage_stock_of_book.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import tay.example.manage_stock_of_book.api.exception.IDNotFoundException
import tay.example.manage_stock_of_book.api.request.CreateBookDTO
import tay.example.manage_stock_of_book.api.request.UpdateBookDTO
import tay.example.manage_stock_of_book.model.Book
import tay.example.manage_stock_of_book.model.Category
import tay.example.manage_stock_of_book.repository.BookRepository
import tay.example.manage_stock_of_book.repository.CategoryRepository
import java.lang.RuntimeException
import java.net.IDN


@Component
class BookService (
        @Autowired
        var bookRepository: BookRepository,

        @Autowired
        var categoryRepository: CategoryRepository
){
        var books: Page<Book>? = null
        var category: Category? = null

        fun getListBook(value: String = "null", categoryId: Long = 0, page: Int = 0, limit: Int = 0): ResponseEntity<Any> {

                if (value != "null" && categoryId != 0.toLong()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(value!!,category!!,PageRequest.of(0,5))
                }

                if (value != "null" && categoryId != 0.toLong() && page !=  0.toInt()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(value!!,category!!,PageRequest.of(page,5))
                }

                if (value != "null" && categoryId != 0.toLong() && limit !=  0.toInt()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(value!!,category!!,PageRequest.of(0,limit))
                }

                if (value != "null" && categoryId != 0.toLong() && page != 0.toInt() && limit != 0.toInt()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(value!!,category!!,PageRequest.of(page,limit))
                }

                if (value == "null" && categoryId == 0.toLong() && page == 0.toInt() && limit == 0.toInt()){
                        books = bookRepository.findAll(PageRequest.of(0,5))

                }

                if (value != "null"){
                        books = bookRepository.findListBook(value,PageRequest.of(0,5))
                }

                if (categoryId != 0.toLong()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(category!!,PageRequest.of(0,5))
                }

                if (page != 0.toInt()){
                        books = bookRepository.findAll(PageRequest.of(page,5))
                }

                if (limit != 0.toInt()){
                        books = bookRepository.findAll(PageRequest.of(0,limit))
                }

                if (page != 0.toInt() && limit != 0.toInt()){
                        books = bookRepository.findAll(PageRequest.of(page,limit))
                }

                if (value != "null" && page != 0.toInt()){
                        books = bookRepository.findListBook(value,PageRequest.of(page,5))
                }

                if (value != "null" && limit != 0.toInt()){
                        books = bookRepository.findListBook(value,PageRequest.of(0,limit))
                }

                if (categoryId != 0.toLong() && page != 0.toInt()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(category!!,PageRequest.of(page,5))
                }

                if (categoryId != 0.toLong() && limit != 0.toInt()){
                        category = categoryRepository.findById(categoryId!!).orElseThrow {
                                IDNotFoundException("categoryID")
                        }
                        books = bookRepository.findListBook(category!!,PageRequest.of(0,limit))
                }

                return ResponseEntity.ok(books!!)
        }

        fun createBook(createBookDTO: CreateBookDTO): ResponseEntity<Any>{
                val category = categoryRepository.findById(createBookDTO.category).orElseThrow{
                        throw IDNotFoundException("categoryID")
                }

                val book = Book.fromDTO(createBookDTO,category)
                return ResponseEntity.ok(bookRepository.save(book).toDTO())
        }

        fun getDetailBook(id: Long): ResponseEntity<Any>{
                try {
                        val book = bookRepository.findById(id).get().toDTO()
                        return ResponseEntity.ok(book)
                }catch (e: RuntimeException){
                        throw IDNotFoundException("bookID")
                }

        }

        fun updateBook(updateBookDTO: UpdateBookDTO, id: Long): ResponseEntity<Any>{
                val book = bookRepository.findById(id).orElseThrow {
                        throw IDNotFoundException("bookID")
                }

//                val category = if (updateBookDTO.category == null)
//                        categoryRepository.findById(book.category.id).orElseThrow{
//                                throw IDNotFoundException("categoryID")
//                        }
//                else categoryRepository.findById(updateBookDTO.category!!).orElseThrow {
//                        throw IDNotFoundException("categoryID")
//                }

                val category = updateBookDTO.let {
                        if (it.category == null){
                                categoryRepository.findById(book.category.id).orElseThrow{
                                        throw IDNotFoundException("categoryID")
                                }
                        }else{
                                categoryRepository.findById(updateBookDTO.category!!).orElseThrow {
                                        throw IDNotFoundException("categoryID")
                                }
                        }
                }

                val updatedBook = Book.fromDTO(updateBookDTO,category,book)
                return ResponseEntity.ok(bookRepository.save(updatedBook).toDTO())
        }

}