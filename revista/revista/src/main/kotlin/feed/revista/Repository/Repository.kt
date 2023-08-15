package feed.revista.Repository

import feed.revista.Model.Post
import org.springframework.boot.autoconfigure.security.SecurityProperties

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PostRepository : JpaRepository<Post, Int> {
    fun findPostByUserOrderById(user: User): List<Post>
    fun findAllByOrderByIdDesc(): List<Post>
}
