package top.kagerou.langbao.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse
import top.kagerou.langbao.service.SystemService

@RestController
class DtsController {

    @Autowired
    val systemService = SystemService()

    /**
     * 接口 /api/dst/start
     * 启动饥荒服务器
     */
    @GetMapping("/api/dst/start")
    fun dstStartAllService(@RequestParam(value = "data") data: Any) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstStartAllService())

    /**
     * 接口/api/dst/stop
     * 关闭饥荒服务器
     */

    @GetMapping("/api/dst/start")
    fun dstStopAllService(@RequestParam(value = "data") data: Any) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstStopAllService())

    /**
     * 接口/api/dst/version
     * 获取游戏的版本号
     */

    @GetMapping("/api/dst/version")
    fun dstGetVersion(@RequestParam(value = "data") data: Any) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstGetVersion())

    /**
     * 接口/api/dst/update
     * 更新游戏版本
     */

    @GetMapping("/api/dst/update")
    fun dstUpdate(@RequestParam(value = "data") data: Any) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstUpdate())

    /**
     * 接口/api/dst/user
     * 获取用户列表
     */

    @GetMapping("/api/dst/user")
    fun dstGetUserList(@RequestParam(value = "data") data: Any) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstGetUserList())

    /**
     * 接口/api/dst/blacklist
     * 把指定用户加入黑名单
     * @param userId
     */
    @GetMapping("/api/dst/blacklist")
    fun dstBlacklist(@RequestParam(value = "userid") userId: Any) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstBlacklist(userId))


    /**
     * 接口/api/dst/file
     * 查看文件内容
     * @param fileName
     */
    @GetMapping("/api/dst/file")
    fun dstFileContext(@RequestParam(value = "filename") fileName: String) =
        ResultResponse(ResultCode.SUCCESS.code, ResultCode.SUCCESS.message,systemService.dstFileContext(fileName))


}