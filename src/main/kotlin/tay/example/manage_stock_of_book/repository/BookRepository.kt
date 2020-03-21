package tay.example.manage_stock_of_book.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import tay.example.manage_stock_of_book.model.Book
import tay.example.manage_stock_of_book.model.Category


interface BookRepository: JpaRepository<Book, Long> {


    @Query("SELECT b FROM Book b where (b.author LIKE %:q% OR " +
            "b.edition LIKE %:q% OR " +
            "b.isbn LIKE %:q% OR " +
            "b.price LIKE %:q% OR " +
            "b.publishYear LIKE %:q% OR " +
            "b.publisher LIKE %:q% OR " +
            "b.quantity LIKE %:q% OR " +
            "b.status=:q OR " +
            "b.title LIKE %:q%) AND " +
            "b.category=:category"

    )
    fun findListBook(@Param(value = "q") q: String,
                     @Param(value = "category") category: Category,
                     pageable: Pageable): Page<Book>


    @Query("SELECT b FROM Book b where (b.author LIKE %:q% OR " +
            "b.edition LIKE %:q% OR " +
            "b.isbn LIKE %:q% OR " +
            "b.price LIKE %:q% OR " +
            "b.publishYear LIKE %:q% OR " +
            "b.publisher LIKE %:q% OR " +
            "b.quantity LIKE %:q% OR " +
            "b.status LIKE %:q% OR " +
            "b.title LIKE %:q%)"

    )
    fun findListBook(@Param(value = "q") q: String, pageable: Pageable): Page<Book>

    @Query("SELECT b FROM Book b WHERE b.category=:category")
    fun findListBook(@Param(value = "category") category: Category, pageable: Pageable): Page<Book>


}