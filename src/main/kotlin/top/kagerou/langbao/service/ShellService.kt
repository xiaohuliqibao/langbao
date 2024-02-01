package top.kagerou.langbao.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import top.kagerou.langbao.util.DstConstant
import top.kagerou.langbao.util.FileUtils
import top.kagerou.langbao.util.ShellUtil
import java.io.File
import java.util.stream.Collectors
import java.util.stream.Stream

@Service
class ShellService {

    val MAX_SLEEP_SECOND = 10

    @Autowired
    val systemService= SystemService()

    /**
     * 获取地面程序进程号
     */
    fun getMasterProcessNum(): String?{
        val result = ShellUtil.execShell(DstConstant.FIND_MASTER_CMD.split(" "))
        return result.ifBlank { null }
    }

    /**
     * 获取洞穴程序进程号
     */
    fun getCaveProcessNum(): String?{
        val result = ShellUtil.execShell(DstConstant.FIND_CAVES_CMD.split(" "))
        return result.ifBlank { null }
    }

    /**
     * 恢复游戏存档
     *
     * @param fileName 备份的游戏名称
     */
    fun revertBackup(fileName: String){
        val command = mutableListOf<String>("cd","~/.klei/DoNotStarveTogether"," ;","rm","-rf","MyDediServer/"," ;","tar","-xvf")
        command.add(fileName)
        ShellUtil.execShell(command)
    }

    /**
     * 获取备份路径下的备份文件列表名称
     */
    fun getBackupList(): List<String>{
        val backupPath = DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + DstConstant.DST_DOC_PATH
        val file = File(backupPath)
        if (!file.exists()) return listOf<String>()
        val files: List<String> = FileUtils.getFilesNames(backupPath)
        if (files.isNotEmpty()) {
            val stream: Stream<String> = files.stream().filter { it ->
                it.contains(DstConstant.BACKUP_FILE_EXTENSION)
            }
            return stream.collect(Collectors.toList())
        }
        return listOf<String>()
    }

    /**
     * 将用户配置的mod安装到游戏中
     * 先读取配置，在写入游戏安装目录的dedicated_server_mods_setup文件中
     */

    fun installModToServer(): String{
        val myGameModPath = DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + DstConstant.DST_USER_GAME_MASTER_MOD_PATH
        val serverModPath = DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + DstConstant.DST_MOD_SETTING_PATH
        val myGameModFile = File(myGameModPath)
        val serverModFile = File(serverModPath)
        if (!myGameModFile.exists()) return "mod文件不存在"
        val modIdList = mutableListOf<String>()
        //读取配置文件行,workshop-1651623054
        myGameModFile.readLines().stream().filter { it -> it.contains("workshop") }.forEach { item -> modIdList.add(item.split("-")[1]) }
        modIdList.forEach { item -> serverModFile.appendText("ServerModSetup(\"{$item}\") \n") }
        return "服务器Mod文件已更新"

    }

}