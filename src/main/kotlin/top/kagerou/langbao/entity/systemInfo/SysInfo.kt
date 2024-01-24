package top.kagerou.langbao.entity.systemInfo

/**
 * 系统相关信息
 * computerName - 服务器名称
 * computerIp - 服务器Ip
 * userDir - 项目路径
 * osName - 操作系统
 * osArch - 系统架构
 */
data class SysInfo(var computerName: String,
                   var computerIp: String,
                   var userDir: String,
                   var osName: String,
                   var osArch: String)