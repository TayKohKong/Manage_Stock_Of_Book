package tay.example.manage_stock_of_book.repository

import org.springframework.data.domain.Page
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import tay.example.manage_stock_of_book.model.Book
import org.springframework.data.domain.*
import tay.example.manage_stock_of_book.api.request.BookDTO


interface BookRepository: JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {


}