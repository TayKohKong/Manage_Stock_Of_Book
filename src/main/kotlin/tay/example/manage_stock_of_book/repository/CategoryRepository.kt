package tay.example.manage_stock_of_book.repository

import org.springframework.data.jpa.repository.JpaRepository
import tay.example.manage_stock_of_book.model.Book
import tay.example.manage_stock_of_book.model.Category

interface CategoryRepository: JpaRepository<Category, Long> {

}