package com.example.morax.model

import com.example.morax.util.MoraxUtils
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors


class Model

@Document
data class User(
    @Id var id: String = "",
    @Indexed(unique = true) val username: String = "",
    @Indexed(unique = true) val email: String = "",
    val displayName: String = "",
    val password: String = "",
    val role: Role = Role.USER
) {
    companion object {
        val currentUser: User = User()

        fun newUser(userReq: UserReq): User {
            val id = MoraxUtils.newUUID()
            return User(
                id,
                username = userReq.username,
                email = userReq.email,
                displayName = userReq.displayName,
                password = userReq.password,
                role = Role.USER
            )
        }
    }
}

data class Artifact(
    val id: String,
    val name: String,
    val time: String,
    val locationId: String,
    val image: MultipartFile
)

data class Fact(
    val id: String,
    val content: String,
    val quizId: String?,
    val artifactId: String?
)

data class Location(
    val id: String,
    val name: String,
    val nameInMap: String,
    val latitude: String,
    val longitude: String
)

data class Quiz(
    val id: String,
    val question: String,
    val correctAnswer: String,
    val point: Int
)

data class Answer(
    val id: String,
    val quizId: String,
    val content: String
)


enum class Role(val permission: Set<Permission>) {
    USER(setOf()),
    MANAGER(
        setOf(
            Permission.MANAGER_READ,
            Permission.MANAGER_UPDATE,
            Permission.MANAGER_DELETE,
            Permission.MANAGER_CREATE
        )
    );

    private val permissions: Set<Permission> = setOf()

    fun getAuthorities(): Collection<SimpleGrantedAuthority> {
        val authorities = permissions
            .stream()
            .map { permission -> SimpleGrantedAuthority(permission.permission) }
            .collect(Collectors.toList())
        authorities.add(SimpleGrantedAuthority("ROLE_$name"))
        return authorities
    }

}

enum class Permission(val permission: String) {
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete")
}

data class Token(
    val id: String,
    val token: String,
    var revoked: Boolean = false,
    var expired: Boolean = false,
    val userId: String
)

