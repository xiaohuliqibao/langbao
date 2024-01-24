package top.kagerou.langbao

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse
import top.kagerou.langbao.entity.SystemInfomation


@SpringBootTest
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
}
