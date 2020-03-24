package tay.example.manage_stock_of_book.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import tay.example.manage_stock_of_book.api.exception.IDNotFoundException
import tay.example.manage_stock_of_book.repository.CategoryRepository
import java.lang.RuntimeException

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
        try{
            val category = categoryRepository.findById(id)
            return ResponseEntity.ok(category)
        }catch (e: RuntimeException){
            throw IDNotFoundException("categoryId")
        }

    }
}