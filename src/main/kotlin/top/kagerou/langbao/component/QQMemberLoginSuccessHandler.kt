package top.kagerou.langbao.component

import com.alibaba.fastjson2.JSON
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse

@Component
class QQMemberLoginSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        response!!.characterEncoding = "utf-8"
        response.contentType = "text/json;charset=utf-8"
        val resultResponse: ResultResponse? = ResultResponse.setResultResponse(ResultCode.SUCCESS)
        response.writer.write(JSON.toJSONString(resultResponse))
    }
}
