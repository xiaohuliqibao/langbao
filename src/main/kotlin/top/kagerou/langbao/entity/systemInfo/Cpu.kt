package top.kagerou.langbao.entity.systemInfo

import lombok.Data
import top.kagerou.langbao.util.Arith


/**
 * CPU相关信息
 *
 */
@Data
class Cpu {
    /**
     * 核心数
     */
    var cpuNum: Int = 0

    /**
     * CPU总的使用率
     */
    var total = 0.0
        // field = this.total
        get() = Arith.round(Arith.mul(field,100.0),2)

    /**
     * CPU系统使用率
     */
    var sys: Double = 0.0
        get() = Arith.round(Arith.mul(field / total, 100.0), 2)

    /**
     * CPU用户使用率
     */
    var used: Double = 0.0
        get() = Arith.round(Arith.mul(field / total, 100.0), 2)

    /**
     * CPU当前等待率
     */
    var wait: Double = 0.0
        get() = Arith.round(Arith.mul(field / total, 100.0), 2)

    /**
     * CPU当前空闲率
     */
    var free: Double = 0.0
        get() = Arith.round(Arith.mul(field / total, 100.0), 2)


}