package top.kagerou.langbao.entity.systemInfo

/**
 * 系统文件相关信息
 * dirName - 盘符路径
 * sysTypeName - 盘符类型
 * typeName - 文件类型
 * total - 总大小
 * free - 剩余大小
 * used - 已经使用量
 * usage - 资源的使用率
 */
data class SysFile(var dirName: String = "",
                   var sysTypeName: String= "",
                   var typeName: String= "",
                   var total: String= "",
                   var free: String= "",
                   var used: String= "",
                   var usage: Double= 0.0)