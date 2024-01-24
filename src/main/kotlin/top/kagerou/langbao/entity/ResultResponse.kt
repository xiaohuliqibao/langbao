package top.kagerou.langbao.entity

import java.io.Serializable

data class ResultResponse( val code: Int,
                           val message: String,
                           val data: Any = "" ,
                           val timeTemp: Long = System.currentTimeMillis()) : Serializable {
    private val serialVersionUID = 1L
}
