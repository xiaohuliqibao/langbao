package top.kagerou.langbao.component

import com.alibaba.fastjson2.JSON
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.*
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import top.kagerou.langbao.entity.ResultCode
import top.kagerou.langbao.entity.ResultResponse


class QQMemberLoginFailHandler : AuthenticationFailureHandler {

    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        response!!.characterEncoding = "utf-8"
        response.contentType = "text/json;charset=utf-8"
        var resultResponse: ResultResponse? = null

        resultResponse = when(exception){
            is AccountExpiredException -> ResultResponse.setResultResponse(ResultCode.USER_ACCOUNT_EXPIRED)
            is BadCredentialsException -> ResultResponse.setResultResponse(ResultCode.USER_CREDENTIALS_ERROR)
            is CredentialsExpiredException -> ResultResponse.setResultResponse(ResultCode.USER_CREDENTIALS_EXPIRED)
            is DisabledException -> ResultResponse.setResultResponse(ResultCode.USER_ACCOUNT_DISABLE)
            is LockedException -> ResultResponse.setResultResponse(ResultCode.USER_ACCOUNT_LOCKED)
            is InternalAuthenticationServiceException -> ResultResponse.setResultResponse(ResultCode.USER_ACCOUNT_NOT_EXIST)
            else -> ResultResponse.setResultResponse(ResultCode.COMMON_FAIL)
        }
        response.writer.write(JSON.toJSONString(resultResponse))
    }
}
