package top.kagerou.langbao.entity.systemInfo

import lombok.Data
import top.kagerou.langbao.util.Arith

@Data
class Memo {

    /**
     * 内存总量
     */
    var total: Double = 0.0
        get() = Arith.div(field,(1024*1024*1024).toDouble(),2)
    /**
     * 已用内存
     */
    var used: Double = 0.0
        get() = Arith.div(field,(1024*1024*1024).toDouble(),2)
    /**
     * 剩余内存
     */
    var free: Double = 0.0
        get() = Arith.div(field,(1024*1024*1024).toDouble(),2)

    /**
    * 内存可用率
    */
    fun getUsage(): Double{
        return Arith.mul(Arith.div(used,total,4),100.0)
    }
}