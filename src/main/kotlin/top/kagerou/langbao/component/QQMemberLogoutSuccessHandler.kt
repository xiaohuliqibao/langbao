package top.kagerou.langbao.component

import com.alibaba.fastjson2.JSON
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse


class QQMemberLogoutSuccessHandler : LogoutSuccessHandler {

    override fun onLogoutSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        response!!.characterEncoding = "utf-8"
        response.contentType = "text/json;charset=utf-8"
        val resultResponse = ResultResponse(ResultCode.LOGOUT_SUCCESS.code,ResultCode.LOGOUT_SUCCESS.message)
        response.writer.write(JSON.toJSONString(resultResponse))
    }
}
