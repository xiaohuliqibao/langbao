package top.kagerou.langbao

import okhttp3.Headers
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.junit.jupiter.api.Test
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse
import top.kagerou.langbao.entity.SystemInformation
import top.kagerou.langbao.util.HttpClientUtil
import java.io.BufferedReader
import java.io.File
import java.io.IOException


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
		val server = SystemInformation()
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
		val mutableListOf = mutableListOf("cmd.exe","/c","ping", "www.baidu.com")
		val pb: ProcessBuilder = ProcessBuilder(mutableListOf)
//		pb.directory(File("C:\\Users\\93031\\AppData\\Local\\Programs\\Git\\usr\\bin"))
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

	@Test
	fun getSteamVersion(){
		val url: String = "https://steamcommunity-a.akamaihd.net/news/newsforapp/v0002"
		val params = mutableMapOf<String,String>()
		params["appid"] = "322330"
		params["count"] = "10"
		params["format"] = "json"
		params["maxlength"] = "10"
		val headers: Headers = Headers.headersOf("user-agent","Valve/Steam HTTP Client 1.0 (0)","Host","steamcommunity-a.akamaihd.net","Accept","text/html,*/*;q=0.9","accept-charset", "ISO-8859-1,utf-8,*;q=0.7")
		val respond = HttpClientUtil.getHttpRespondToString(url, params, headers)
		val newsItems = com.alibaba.fastjson2.JSONObject.parseObject(respond).getJSONObject("appnews").getJSONArray("newsitems")
		for (i in 0..< newsItems.size){
			val title = newsItems.getJSONObject(i).getString("title")
			if (title.contains("Hotfix")) {
				println(title)
				break
			}
		}
		println("链接：$url")
	}
}
