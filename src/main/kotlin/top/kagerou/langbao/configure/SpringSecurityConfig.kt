package top.kagerou.langbao.configure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

/**
 * @author Eleftheria Stein
 */
@Configuration
@EnableWebSecurity
class SpringSecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/api/**", permitAll)
                authorize("/user/**", hasAuthority("ROLE_USER"))
            }
            formLogin {
                loginPage = "/login"
            }
        }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetails = User.withDefaultPasswordEncoder()
            .username("qibao")
            .password("Xiaohuli")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(userDetails)
    }
}