package top.kagerou.langbao.util

import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import com.alibaba.fastjson2.JSONObject
import org.springframework.http.HttpHeaders
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy


object HttpClientUtil {
    private val client = OkHttpClient()
    val JSON: MediaType = "application/json".toMediaType()
    private const val IMAGE_PATH = "/home/qibao/file/miraibot/images/"
    private const val IMAGE_PATH_DEV = " D:/"
    private const val IMAGE_FORMAT = ".jpg"
    val aliproxy: Proxy = Proxy(Proxy.Type.SOCKS, InetSocketAddress("106.14.32.210", 38081))
    /**
     * GET同步请求网络API获取基本的数据
     * @author qibao/xiaohuliqibao@gmail.com
     * @creatTime 2021/11/20 9:41
     * @param  url API地址
     * @param  params 请求参数
     * @param  headers 请求头
     * @return String
     */
    fun getHttpRespondToString(url: String,params: MutableMap<String,String>,headers: Headers): String {
        val httpUrl = url.toHttpUrl().newBuilder()
        var responseStr = ""
        for (param in params){
            httpUrl.addQueryParameter(param.key,param.value)
        }
        val request = Request.Builder().url(httpUrl.build()).headers(headers).build();
        try {
            val response = client.newCall(request).execute()
            responseStr =  response.body!!.string()
            if(response.body != null) {
                response.body!!.close();
            }
        }catch (e: IOException){
            return "Get Request Error!"
        }
        return responseStr
    }

    fun getHttpRespondToString(url: String,params: MutableMap<String,String>): String{
        val headers = Headers.headersOf("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
        return getHttpRespondToString(url, params, headers)
    }

    /**
     * POST同步请求网络API获取基本的数据
     * @author qibao/xiaohuliqibao@gmail.com
     * @creatTime 2021/11/20 9:41
     * @param  url API地址
     * @param  forms 请求参数
     * @param  headers 请求头
     * @return String
     */
    fun postHttpRespondToString(url: String,forms: MutableMap<String,String>,headers: Headers): String{
        val formBody = FormBody.Builder()
        var responseStr = ""
        for (item in forms){
            formBody.add(item.key,item.value)
        }

        val request = Request.Builder().url(url).headers(headers).post(formBody.build()).build();
        try {
            val response = client.newCall(request).execute()
            responseStr =  response.body!!.string()
            if(response.body != null) {
                response.body!!.close();
            }
        }catch (e: IOException){
            return "Post Request Error!"
        }
        return responseStr
    }

    /**
     * 请求头 默认为Mozilla/5.0 (Windows NT 10.0; Win64; x64)......
     */
    fun postHttpRespondToString(url: String,forms: MutableMap<String,String>):String{
        val headers = Headers.headersOf("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
        return postHttpRespondToString(url,forms,headers)
    }
    fun postHttpRespond(url: String, forms: MutableMap<String, String>, headers: Headers): Response {
        var formBody = FormBody.Builder()
        for (item in forms) {
            formBody.add(item.key, item.value)
        }
//        println(formBody.toString())

        val request = Request.Builder().url(url).headers(headers).post(formBody.build()).build();
        return client.newCall(request).execute()
        client.newCall(request).execute().close()
    }
    fun deleteHttpRespondToString(url: String,forms: MutableMap<String,String>,headers: Headers): String{
        var formBody = FormBody.Builder()
        for (item in forms){
            formBody.add(item.key,item.value)
        }
        val request = Request.Builder().url(url).headers(headers).delete(formBody.build()).build();
        try {
            val response = client.newCall(request).execute()
            return response.body!!.string()
            if(response.body != null) {
                response.body!!.close();
            }
        }catch (_: IOException){
        }
        return "Delete Request Error!"
    }
    fun getImageFile(imagePath: String,imageUrl: String,imageTitle: String,imageEXT: String): File{
        val imageFile = File("$imagePath$imageTitle.$imageEXT")
        val fos: FileOutputStream
        val request = Request.Builder().url(imageUrl).headers(Headers.headersOf("Referer","https://www.pixiv.net/","User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")).build()
        try {
            fos = FileOutputStream(imageFile)
            val response = client.newBuilder().build().newCall(request).execute()
            fos.write(response.body!!.bytes())
            fos.close()
            response.close()
        }catch (e:IOException){
            println("下载图片出错。原因：${e.message}")
        }
        return imageFile
    }

}