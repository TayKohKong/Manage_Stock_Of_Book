package tay.example.manage_stock_of_book.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import tay.example.manage_stock_of_book.api.request.BookDTO
import tay.example.manage_stock_of_book.api.request.CreateBookDTO
import tay.example.manage_stock_of_book.api.request.UpdateBookDTO
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "books")
data class Book(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        var id: Long = 0,

        @Column(name = "title")
        var title: String,

        @Column(name = "isbn")
        var isbn: String,

        @Column(name = "author")
        var author: String,

        @Column(name = "publisher")
        var publisher: String,

        @Column(name = "edition")
        var edition: String,

        @Column(name = "price")
        var price: Float,

        @Column(name = "quantity")
        var quantity: Int,

        @Column(name = "status")
        var status: String,

        @Column(name = "publish_year")
        var publishYear: Int,

        @CreationTimestamp
        @Column(name ="created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: LocalDateTime = LocalDateTime.now()
){
    @ManyToOne
    @JoinColumn(name= "category_id")
    lateinit var category: Category

    fun toDTO(): BookDTO = BookDTO(
            id = id,
            title = title,
            isbn = isbn,
            author = author,
            publisher = publisher,
            edition = edition,
            price = price,
            quantity = quantity,
            status = status,
            publish_year = publishYear,
            category = category.id
    )


    companion object{
        fun fromDTO(dto: CreateBookDTO, category: Category): Book{
            val book = Book(
                    title = dto.title,
                    isbn = dto.isbn,
                    author = dto.author,
                    publisher = dto.publisher,
                    edition = dto.edition,
                    price = dto.price,
                    quantity = dto.quantity,
                    status = dto.status,
                    publishYear = dto.publish_year
            )
            book.category = category
            return book
        }

        fun fromDTO(newDTO: UpdateBookDTO, category: Category?, oldBook: Book): Book{
            val book = Book(
                    id = oldBook.id,
                    title = newDTO.title ?: oldBook.title,
                    isbn = newDTO.isbn ?: oldBook.isbn,
                    author = newDTO.author ?: oldBook.author,
                    publisher = newDTO.publisher ?: oldBook.publisher,
                    edition = newDTO.edition ?: oldBook.edition,
                    price = newDTO.price ?: oldBook.price,
                    quantity = newDTO.quantity ?: oldBook.quantity,
                    status = newDTO.status ?: oldBook.status,
                    publishYear = newDTO.publish_year ?: oldBook.publishYear
            )
            book.category = category!!
            return book
        }
    }
}