package top.kagerou.langbao.util

import java.io.File
import java.util.*

object FileUtils {
    /**
     * 读取指定路径下的所有文件
     *
     * @param path 文件路径
     * @return 文件列表
     */
    fun getFiles(path: String): List<String>{
        val files = mutableListOf<String>()
        val file = File(path)
        val listFiles = file.listFiles()
        if (listFiles != null) {
            for (item in listFiles){
                if (item.isFile){
                    files.add(item.toString())
                }
            }
        }
        return files
    }

    /**
     * 获取指定路径下所有文件名称
     *
     * @param path 路径
     * @return 文件名称
     */
   fun getFilesNames(path: String): List<String>{
       val files = mutableListOf<String>()
        val dir = File(path)
        val filesList = dir.list()
        if (filesList !=null){
            for (it in filesList){
                files.add(it)
            }
        }
        return files
   }

    /**
     * 创建目录
     *
     * @param path 如 /home/ubuntu/.klei/DoNotStarveTogether/MyDediServer/Master
     * @return true 创建成功
     */
    fun mkdirs(path: String): Boolean{
        val file = File(path)
        if (!file.exists())
        {
            return file.mkdirs()
        }
        return true
    }

    /**
     * 读取指定位置文件
     *
     * @param filePath 文件路径
     */

    fun readFile(path: String): String {
        val file = File(path)
        if (!file.exists()){
            return String()
        }
        return file.readText()
    }

    /**
     * 写入文件到指定位置
     *
     * @param filePath 文件路径
     * @param context  内容
     */
    fun writeInFile(path: String,context: String): String{
        val file = File(path)
        file.writeText(context)
        return readFile(path)
    }

    /**
     * 读取文件指定N行到M行
     * 根据换行符判断当前的行数，
     * 使用统计来判断当前读取第N行
     *
     * @param path    待文件
     * @param startLine 读取的开始行数，-1为文件第一行
     * @param endLine 读取的结束行数，-1为文件最后一行
     */
    fun readCustomerLine(path: String,startLine: Int ,endLine: Int ): String{
        val file = File(path)
        val fileContext = mutableListOf<String>()
        if (!file.exists()|| file.isDirectory || !file.canRead())
        {
            return String()
        }
        val readLines = file.readLines()
        for (i in startLine..endLine){
            fileContext.add(readLines[i])
        }
        return fileContext.toString()
    }

    /**
     * 给脚本文件授权
     * @param path 文件路径
     * @param filename 文件名称
     * @return 执行内容
     */
    fun chmod(path: String,filename:String): String{
        val command = mutableListOf<String>("cd",path,";","chmod","+x",filename)
        val execContext = ShellUtil.execShell(command)
        return "给{$path}下的{$filename}增加了执行权限。更多信息：{$execContext}"
    }
}
