package tay.example.manage_stock_of_book.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "categories")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        var id: Long = 0,

        @Column(name = "name")
        var name: String,

        @Column(name = "description")
        var description: String,

        @CreationTimestamp
        @Column(name ="created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: LocalDateTime = LocalDateTime.now()
)