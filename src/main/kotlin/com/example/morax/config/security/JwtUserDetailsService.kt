package com.example.morax.config.security

import com.example.morax.model.User
import com.example.morax.model.UserResp
import com.example.morax.repo.UserRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException

@Service
class JwtUserDetailsService(private val userRepo: UserRepo): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return try {
            val user: User = userRepo.findUserByEmail(username)
            User.currentUser.id = user.id
            CustomUserDetails(user)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }
}