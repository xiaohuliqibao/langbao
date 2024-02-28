package top.kagerou.langbao.util

import com.alibaba.fastjson2.JSONObject
import okhttp3.Headers
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

object DstUtil {

    private val versionFilePath =  DstConstant.ROOT_PATH + DstConstant.SINGLE_SLASH + "/dst/version.txt"

    /**
     * 通过访问steam游戏频道新闻来找到Hotfix词条获取当前最新的游戏版本。
     */
    fun getSteamVersion(): String {
        var version = String()
        val url: String = "https://steamcommunity-a.akamaihd.net/news/newsforapp/v0002/"
        val params = mutableMapOf<String,String>()
        params["appid"] = "322330"
        params["count"] = "5"
        params["format"] = "json"
        params["maxlength"] = "10"
        val headers: Headers = Headers.headersOf("user-agent","Valve/Steam HTTP Client 1.0 (0)","Host","steamcommunity-a.akamaihd.net","Accept","text/html,*/*;q=0.9","accept-encoding", "gzip,identity,*;q=0","accept-charset", "ISO-8859-1,utf-8,*;q=0.7")
        // 构建 客户端
        val client = OkHttpClient()
        // 构建 HttpUrl并传入请求参数
        val httpUrl = url.toHttpUrl().newBuilder()
        for(param in params){
            httpUrl.addQueryParameter(param.key,param.value)
        }
        // 构建 完整请求
        val request = Request.Builder().url(httpUrl.build()).headers(headers).build()
        try {
            val response = client.newCall(request).execute()
            val responseStr =  response.body!!.string()
            val parseObject = JSONObject.parseObject(responseStr)
            val newsList = parseObject.getJSONObject("appnews").getJSONArray("newsitems")
            // public class JSONArray extends ArrayList<Object>
            // JSONArray 继承为ArrayList，数据类型为Object也就是Kotlin的Any,使用通常的方式for in 或者 foreach得到对象为Any，无法在使用JSONObject的性质继续操作，所以使用getJSONObject(index: Int)方法来处理遍历的问题
            for(i in newsList.size downTo  0)
            {
                val news: JSONObject = newsList.getJSONObject(i)
                if (news.getString("title").contains("Hotfix")) {
                    version = news.getString("title").split(" ")[1]
                }
            }
            response.body!!.close()
        }catch (e: Exception)
        {
            return "error"
        }

        return version
    }

     /**
     * 通过读取本地文件获取本地游戏版本
     */

    fun getServerVersion(): String =  FileUtils.readCustomerLine(versionFilePath,1,1)

}
