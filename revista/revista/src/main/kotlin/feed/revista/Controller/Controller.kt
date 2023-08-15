package feed.revista.Controller

import UserDto
import feed.revista.Model.Post
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.net.URI

public class Controller {
    @PostMapping("addpost")
    fun addPost(@RequestBody post: Post): ResponseEntity<*> {
        val user: UserDto = securityService.getUser()
        val savedPost: Post = postService.savePost(user, post.content)
        return ResponseEntity.created(URI.create("/private/mypost")).body(savedPost)
    }

    @GetMapping("mypost")
    fun myPosts(): ResponseEntity<*> {
        val user: SecurityProperties.User = userService.getUser(securityService.getUser().email)
        val postList: List<Post> = postService.getPostsOfUser(user.id)
        return ResponseEntity.ok(postList)
    }

    @GetMapping("posts")
    fun getAllPosts(): ResponseEntity<List<Post>> {
        val postList: List<Post> = postService.getAllPost()
        return ResponseEntity.ok(postList)
    }

    @GetMapping("/{userId}/posts")
    fun getPostofUser(@PathVariable userId: Int): ResponseEntity<*> {
        val postList: List<Post> = postService.getPostsOfUser(userId)
        return ResponseEntity.ok(postList)
    }
}


