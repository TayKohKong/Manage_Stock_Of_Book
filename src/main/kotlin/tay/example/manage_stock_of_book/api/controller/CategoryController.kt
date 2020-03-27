package tay.example.manage_stock_of_book.api.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tay.example.manage_stock_of_book.service.CategoryService


@RestController
@RequestMapping(value = ["/categories"])
class CategoryController(
        @Autowired
        var categoryService: CategoryService
) {

    @GetMapping
    fun getAllCategory(): ResponseEntity<Any>{
        return ResponseEntity.ok(categoryService.getAllCategory())
    }


    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): ResponseEntity<Any>{
        return ResponseEntity.ok(categoryService.getCategoryById(id))
    }

}
