package com.dnunezv.u4_actividad3_creacion_app.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {
    @Bean
    fun getPasswordEncoder(): PasswordEncoder
        = BCryptPasswordEncoder()

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain
        = http.authorizeHttpRequests {
            it.requestMatchers("/perform_login").permitAll()
            it.requestMatchers("/login").permitAll()
            it.requestMatchers("/css/**", "/js/**").permitAll()
            it.anyRequest().authenticated()
        }
        .formLogin {
            it.loginPage("/login")
            it.defaultSuccessUrl("/dashboard", true)
            it.usernameParameter("username")
            it.passwordParameter("password")
            it.failureUrl("/login?error=true")
            it.loginProcessingUrl("/perform_login")
            it.permitAll()
        }
        .logout {
            it.logoutUrl("/perform_logout")
            it.logoutSuccessUrl("/login")
            it.permitAll()
        }
        .build()
    @Bean
    fun demoUserDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService
    = InMemoryUserDetailsManager()
        .let {
            it.createUser(
                User(
                    "user",
                    passwordEncoder.encode("password"),
                    listOf(SimpleGrantedAuthority("ROLE_USER"))
                )
            )
            it
        }
}
