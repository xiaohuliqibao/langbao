package top.kagerou.langbao.service

import org.springframework.stereotype.Service
import top.kagerou.langbao.entity.SystemInformation
import top.kagerou.langbao.util.DstConstant
import top.kagerou.langbao.util.DstUtil
import top.kagerou.langbao.util.FileUtils
import top.kagerou.langbao.util.ShellUtil

@Service
class SystemService {
     open fun getSysInfo(): SystemInformation{
         val server = SystemInformation()
         server.copyTo()
         return server
     }

    /**
     * 拉取dst游戏日志
     * @param type MASTER 地面日志 ，CAVE 洞穴日志 CHAT 玩家聊天记录
     * @param rowNum 日志的行数，从后开始取
     * @return 日志
     */

    fun getDstLog(type: String,rowNum: Int):String{
        var logPath = String()
        logPath = when(type){
            "CAVE" -> DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + DstConstant.DST_CAVES_SERVER_LOG_PATH
            "CHAT" -> DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + DstConstant.DST_MASTER_SERVER_CHAT_LOG_PATH
            else -> DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + DstConstant.DST_MASTER_SERVER_CHAT_LOG_PATH
        }
        var result = FileUtils.readCustomerLine(logPath,rowNum,-1)
        if (result.isEmpty()){
            result = "日志不存在：$logPath"
        }
        return result
    }

    /**
     * 获取任务时间列表
     * @return 数据
     */

    //todo
    fun getScheduleList(){}




    // todo 启动服务流程为先完全关闭都再启动
    fun dstStartAllService(): Any = ""
    // todo 关闭服务流程
    fun dstStopAllService(): Any = ""
    /**
     * 获取服务器的版本号
     * @return 版本号
     */
    fun dstGetVersion() : Map<String,String>{
        val steamVersion = DstUtil.getSteamVersion()
        val serverVersion = DstUtil.getServerVersion()
        val versionInfo = mutableMapOf<String,String>()
        versionInfo["steamVersion"] = steamVersion
        versionInfo["serverVersion"] = serverVersion
        return versionInfo
    }
    /**
     * 获取服务器游戏版本
     * @return 控制台输出
     */
    fun dstUpdate(): String {
        dstStopAllService()
        // todo 所有的指令到时候都要测试一遍
        return ShellUtil.execShell(mutableListOf(DstConstant.UPDATE_GAME_CMD))

    }
    // todo 用户列表应该是两部分，一部分是服务器目前在线的用户，另一部分是黑名单用户
    fun dstGetUserList(): Any = ""
    // todo 把指定用户加入到黑名单中，如果用户存在了，则移除黑名单
    fun dstBlacklist(userId: Any): Any = ""
    // todo 可查看的文件应该写到数据库中把 id_filename_filepath，只能产看指定的文件
    fun dstFileContext(fileName: String): Any =""

}
