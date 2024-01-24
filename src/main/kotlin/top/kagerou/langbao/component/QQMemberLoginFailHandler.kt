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
        lateinit var resultResponse: ResultResponse

        resultResponse = when(exception){
            is AccountExpiredException -> ResultResponse(ResultCode.USER_ACCOUNT_EXPIRED.code,ResultCode.USER_ACCOUNT_EXPIRED.message)
            is BadCredentialsException -> ResultResponse(ResultCode.USER_CREDENTIALS_ERROR.code,ResultCode.USER_CREDENTIALS_ERROR.message)
            is CredentialsExpiredException -> ResultResponse(ResultCode.USER_CREDENTIALS_EXPIRED.code,ResultCode.USER_CREDENTIALS_EXPIRED.message)
            is DisabledException -> ResultResponse(ResultCode.USER_ACCOUNT_DISABLE.code,ResultCode.USER_ACCOUNT_DISABLE.message)
            is LockedException -> ResultResponse(ResultCode.USER_ACCOUNT_LOCKED.code,ResultCode.USER_ACCOUNT_LOCKED.message)
            is InternalAuthenticationServiceException -> ResultResponse(ResultCode.USER_ACCOUNT_NOT_EXIST.code,ResultCode.USER_ACCOUNT_NOT_EXIST.message)
            else -> ResultResponse(ResultCode.COMMON_FAIL.code,ResultCode.COMMON_FAIL.message)
        }
        response.writer.write(JSON.toJSONString(resultResponse))
    }
}
