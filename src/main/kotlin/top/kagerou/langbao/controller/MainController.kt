package top.kagerou.langbao.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Eleftheria Stein
 */
@RestController
class MainController {

    @GetMapping("/api/index")
    fun index(): String {
        return "index"
    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}