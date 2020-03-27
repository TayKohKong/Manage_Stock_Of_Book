package tay.example.manage_stock_of_book.spec

import org.springframework.data.jpa.domain.Specification
import tay.example.manage_stock_of_book.model.Book
import tay.example.manage_stock_of_book.model.Category
import javax.persistence.criteria.JoinType


class BookSpec {
    companion object{
        fun searchLikeBookSpec(value:String): Specification<Book>{
            return Specification{root, _, criteriaBuilder ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get<String>("title")),"%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get<String>("isbn")),"%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get<String>("author")),"%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get<String>("publisher")),"%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get<String>("edition")),"%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get<String>("status")),"%$value%")
                )
            }
        }

        fun searchEqualBookSpec(value: String): Specification<Book>{
            return Specification{root, _, criteriaBuilder ->
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get<Float>("price"),value.toFloat()),
                        criteriaBuilder.equal(root.get<Int>("quantity"),value.toInt()),
                        criteriaBuilder.equal(root.get<Int>("publishYear"),value.toInt())

                )
            }
        }

        fun searchListBookByCategoryId(id: Long): Specification<Book>{
            return Specification { root, _, criteriaBuilder ->
                val category = root.join<Book,Category>("category",JoinType.INNER)
                criteriaBuilder.equal(category.get<Long>("id"),id)
            }
        }

    }
}