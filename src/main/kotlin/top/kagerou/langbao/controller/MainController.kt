package top.kagerou.langbao.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse
import top.kagerou.langbao.entity.SystemInfomation
import top.kagerou.langbao.service.SystemService


@RestController
class MainController {

    @Autowired
    val systemService = SystemService()

    @GetMapping("/api/index")
    fun index(): String {
        return "index"
    }

    @GetMapping("/user/index")
    fun userIndex(): String {
        return "user/index"
    }

    @GetMapping("/api/param")
    fun getParam(@RequestParam(value = "data") data: Any) =
        ResultResponse(ResultCode.SUCCESS.code,ResultCode.SUCCESS.message,data)

    @GetMapping("/api/system/info")
    fun getSystemInfo() =
        ResultResponse(ResultCode.SUCCESS.code,ResultCode.SUCCESS.message,systemService.getSysInfo())


    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}