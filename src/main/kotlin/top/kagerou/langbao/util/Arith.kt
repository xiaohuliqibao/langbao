package top.kagerou.langbao.util

import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.math.RoundingMode


object Arith {
    private var DEF_DIV_SCALE: Int = 10

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    fun add(v1: Double,v2: Double): Double{
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.add(b2).toDouble()
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    fun sub(v1: Double,v2: Double): Double{
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.subtract(b2).toDouble()
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    fun mul(v1: Double,v2: Double): Double{
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        return b1.multiply(b2).toDouble()
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */

    fun div(v1: Double,v2: Double,scale: Int): Double {
        if (scale < 0){
            throw IllegalArgumentException("The scale must be a positive integer or zero")
        }
        val b1 = BigDecimal(v1.toString())
        val b2 = BigDecimal(v2.toString())
        if (b1.compareTo(BigDecimal.ZERO) == 0){
            return BigDecimal.ZERO.toDouble()
        }
        return b1.divide(b2,scale,RoundingMode.HALF_UP).toDouble()
    }
    fun div(v1: Double,v2: Double): Double{
        return div(v1,v2,DEF_DIV_SCALE)
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    fun round(v: Double,scale: Int): Double{
        if (scale < 0){
            throw IllegalArgumentException("The scale must be a positive integer or zero")
        }
        val b = BigDecimal(v.toString())
        val one = BigDecimal("1")
        return b.divide(one,scale,RoundingMode.HALF_UP).toDouble()
    }
}