package top.kagerou.langbao.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse
import top.kagerou.langbao.service.SystemService


/**
 * @author Eleftheria Stein
 */
@RestController
class MainController {

    @Autowired
    var systemService: SystemService? = null

    @GetMapping("/api/index")
    fun index(): String {
        return "index"
    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }

    @GetMapping("/api/system/info")
    fun getSystemInfo(): ResultResponse {
        val systemInfo = systemService?.getSysInfo()
        return if (systemInfo != null) {
            ResultResponse(ResultCode.SUCCESS.code,ResultCode.SUCCESS.message, systemInfo)
        } else {
            ResultResponse(ResultCode.COMMON_FAIL.code,ResultCode.COMMON_FAIL.message, "获取文件列表失败！")
        }
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}