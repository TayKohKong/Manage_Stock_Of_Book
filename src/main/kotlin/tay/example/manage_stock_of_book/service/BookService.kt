package tay.example.manage_stock_of_book.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import tay.example.manage_stock_of_book.api.exception.IDNotFoundException
import tay.example.manage_stock_of_book.api.request.CreateBookDTO
import tay.example.manage_stock_of_book.api.request.UpdateBookDTO
import tay.example.manage_stock_of_book.model.Book
import tay.example.manage_stock_of_book.model.Category
import tay.example.manage_stock_of_book.repository.BookRepository
import tay.example.manage_stock_of_book.repository.CategoryRepository
import tay.example.manage_stock_of_book.spec.BookSpec
import java.lang.*


@Component
class BookService (
        @Autowired
        var bookRepository: BookRepository,

        @Autowired
        var categoryRepository: CategoryRepository

){

        fun getListBook(value: String?, categoryId: Long?, page: Int, limit: Int): ResponseEntity<Any> {
                val pageable = PageRequest.of(page,limit)
                var spec: Specification<Book> = Specification.where(BookSpec.searchLikeBookSpec(value!!)!!)!!
                value.toIntOrNull()?.let {
                        spec = spec!!.or(BookSpec.searchEqualBookSpec(it.toString()))!!

                }
                categoryId.let {
                        spec = spec!!.and(BookSpec.searchListBookByCategoryId(categoryId!!))!!
                }
                val books = bookRepository.findAll(spec!!,pageable).map {
                        it.toDTO()
                }

                return ResponseEntity.ok(books)
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
                var category:Category? = null
                val book = bookRepository.findById(id).orElseThrow {
                        throw IDNotFoundException("bookID")
                }

                updateBookDTO.category.let {
                        category = categoryRepository.findById(it!!).orElseThrow{
                                throw IDNotFoundException("categoryID") }
                }

                updateBookDTO.let {
                        category = categoryRepository.findById(book.category.id).orElseThrow{
                                throw IDNotFoundException("categoryID") }
                }

                val updatedBook = Book.fromDTO(updateBookDTO,category,book)
                return ResponseEntity.ok(bookRepository.save(updatedBook).toDTO())
        }

}