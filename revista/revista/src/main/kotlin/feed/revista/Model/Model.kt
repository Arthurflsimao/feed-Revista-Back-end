package feed.revista.Model

import org.springframework.boot.autoconfigure.security.SecurityProperties
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "posts")
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Integer? = null
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private var user: User? = null
    @Column(columnDefinition = "TEXT")
    private var content: String? = null
    @Column(name = "createdDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private var createdDate: Date? = null

    constructor() {}

    fun getId(): Integer? {
        return id
    }

    fun setId(id: Integer?) {
        this.id = id
    }

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User?) {
        this.user = user
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getCreatedDate(): Date? {
        return createdDate
    }

    fun setCreatedDate(createdDate: Date?) {
        this.createdDate = createdDate
    }
}