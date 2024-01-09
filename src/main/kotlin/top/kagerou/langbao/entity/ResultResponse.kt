package top.kagerou.langbao.entity

import lombok.Data
import java.io.Serializable

@Data
class ResultResponse(private var code: Int,
                     private var message: String,
                     private var data: Any,
                     private var timeTemp: Long = System.currentTimeMillis()) : Serializable {
    private val serialVersionUID = 1L

    private fun setResultCode(resultCode: ResultCode){
        this.code = resultCode.code
        this.message = resultCode.message
    }

    companion object {
        fun setResultResponse(resultCode: ResultCode): ResultResponse? {
            val resultResponse: ResultResponse? =null
            resultResponse?.setResultCode(resultCode)
            return resultResponse
        }
    }

}
