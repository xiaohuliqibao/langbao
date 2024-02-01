package top.kagerou.langbao

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse
import top.kagerou.langbao.entity.SystemInfomation
import java.io.BufferedReader
import java.io.File


//@SpringBootTest()
class LangbaoApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun testResponseCLASS(){
		println("------Test start------")
		println(ResultResponse(ResultCode.LOGOUT_SUCCESS.code,"错误").toString())
	}

	@Test
	fun testSystemInfo(){
		println("------Test SystemInfo start------")
		val server = SystemInfomation()
		server.copyTo()
		val cpuNum = server.cpu.cpuNum
		val used = server.cpu.used
		val memoTotal = server.memo.total
		val memoFree = server.memo.free
		println("核心数量：$cpuNum")
		println("CPU使用率：$used")
		println("内存总量：$memoTotal")
		println("空闲内存：$memoFree")
	}

	@Test
	fun testShell(){
		val mutableListOf = mutableListOf("git", "-v")
		val pb: ProcessBuilder = ProcessBuilder(mutableListOf)
		pb.directory(File("C:\\Users\\93031\\AppData\\Local\\Programs\\Git\\usr\\bin"))
		val its = pb.start().inputStream.bufferedReader().use(BufferedReader::readText)
		println("its:$its")
	}

	@Test
	fun readFileText(){
		val filePath: String = "D:\\Users\\test.txt"
		val context: String = "hello world! i am qibao"
		val file = File(filePath)
		if (!file.exists())
		{
			println("文件不存在")
		}

		context.split(" ").forEach{
			file.appendText("$it \n")
		}
		println(file.readText())
	}
}
