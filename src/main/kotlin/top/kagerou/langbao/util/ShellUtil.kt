package top.kagerou.langbao.util

import lombok.extern.java.Log
import lombok.extern.slf4j.Slf4j
import java.io.BufferedReader

@Slf4j
object ShellUtil {
    /**
     * 运行shell脚本
     *
     * @param shell 需要运行的shell脚本
     */
    fun execShell(shell: List<String>): String{
        var its = String()
        var ers = String()
        try {
            val pb = ProcessBuilder(shell)
            val process = pb.start()
            its = process.inputStream.bufferedReader().use(BufferedReader::readText)
            ers = process.errorStream.bufferedReader().use(BufferedReader::readText)
            process.destroy()
        }
        catch (e:Exception){
            println("e:${e.toString()}")
        }
        println("shell:$shell")
        println("result:$its")
        println("error:$ers")
        return its
    }
}