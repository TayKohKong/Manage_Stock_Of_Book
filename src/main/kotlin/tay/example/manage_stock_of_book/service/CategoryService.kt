package tay.example.manage_stock_of_book.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import tay.example.manage_stock_of_book.repository.CategoryRepository

@Component
class CategoryService (
        @Autowired
        var categoryRepository: CategoryRepository
){
    fun getAllCategory(): ResponseEntity<Any>{
        val categories = categoryRepository.findAll()
        return ResponseEntity.ok(categories)
    }

    fun getCategoryById(id : Long): ResponseEntity<Any>{
        val category = categoryRepository.findById(id)
        return ResponseEntity.ok(category)
    }
}