package top.kagerou.langbao.configure

import com.sun.tools.javac.jvm.PoolConstant.LoadableConstant.Long
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import top.kagerou.langbao.component.QQMemberLoginFailHandler
import top.kagerou.langbao.component.QQMemberLoginSuccessHandler
import top.kagerou.langbao.component.QQMemberLogoutSuccessHandler
import top.kagerou.langbao.entity.QQMember
import top.kagerou.langbao.service.QQMemberService
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames.number


/**
 * @author Eleftheria Stein
 */
@Configuration
@EnableWebSecurity
class SpringSecurityConfig {

    @Autowired
    var qqMemberLoginSuccessHandler: QQMemberLoginSuccessHandler? = null

    @Autowired
    var qqMemberLoginFailHandler: QQMemberLoginFailHandler? = null

    @Autowired
    var qqMemberLogoutSuccessHandler: QQMemberLogoutSuccessHandler? = null

    @Autowired
    //todo QQMemberService
    var qqMemberService: QQMemberService? = null
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/api/**", permitAll)
                authorize("/user/**", hasAuthority("ROLE_USER"))

            }
            formLogin {
                loginProcessingUrl = "/login"
                permitAll
                authenticationSuccessHandler = qqMemberLoginSuccessHandler
                authenticationFailureHandler = qqMemberLoginFailHandler
            }
            logout {
                logoutUrl = "/logout"
                permitAll
                logoutSuccessHandler = qqMemberLogoutSuccessHandler
            }
            csrf {
                disable()
            }
        }
        return http.build()
    }

    //todo
    @Bean
    fun userDetailsService(): UserDetailsService {
        val users = InMemoryUserDetailsManager()
        val qqMember: QQMember? = qqMemberService?.getQQMemberByNumber(1L)
        val username: String = qqMember?.number.toString()
        val password: String? = qqMember?.password
        val authorities = ArrayList<SimpleGrantedAuthority>()
        users.createUser(User.withUsername("javaboy").password("{noop}123").roles("admin").build())
        return users
    }

}