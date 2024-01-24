package top.kagerou.langbao.entity

enum class ResultCode(val code: Int, val message: String) {
    SUCCESS(2000, "访问成功"),
    LOGOUT_SUCCESS(2001, "注销成功"),
    COMMON_FAIL(4999, "访问失败"),
    USER_NOT_LOGIN(4001, "账号未登录"),
    USER_ACCOUNT_EXPIRED(4002, "账号已过期"),
    USER_CREDENTIALS_ERROR(4003, "账号密码错误"),
    USER_CREDENTIALS_EXPIRED(4004, "账号密码过期"),
    USER_ACCOUNT_DISABLE(4005, "账号不可用"),
    USER_ACCOUNT_LOCKED(4006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(4007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(4008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(4009, "账号已下线"),
    USER_UNAUTHORIZED(4010, "账号权限不足");

    fun getMessageByCode(code: Int): String? {
        for (element in entries) {
            if (element.code == code) {
                return element.message
            }
        }
        return null
    }
}