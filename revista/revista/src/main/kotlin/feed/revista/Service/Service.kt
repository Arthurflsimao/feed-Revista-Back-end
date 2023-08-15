import feed.revista.Model.Post
import feed.revista.Repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.stereotype.Service

@Service
class PostService {
    @Autowired
    lateinit var postRepository: PostRepository
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var modelMapper: ModelMapper
    @Autowired
    lateinit var securityService: SecurityService

    fun savePost(userDto: UserDto, content: String): Post {
        val post = Post()
        val user = userRepository.findUserByEmail(userDto.email)
        post.user = user
        post.content = content
        return postRepository.save(post)
    }

    fun getPostsOfUser(userId: Int): List<PostDto> {
        val postList = postRepository.findPostByUserOrderById(userRepository.findUserById(userId))
        val postDtoList = ArrayList<PostDto>()
        for (post in postList) {
            postDtoList.add(modelMapper.map(post, PostDto::class.java))
        }
        return postDtoList
    }

    fun getAllPost(): List<Post> {
        return postRepository.findAllByOrderByIdDesc()
    }
}