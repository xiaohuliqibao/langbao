package top.kagerou.langbao.entity

import lombok.Data
import java.io.Serial
import java.io.Serializable
import java.util.*

@Data
class QQMember : Serializable {
    @Serial
    private val serialVersionUID = -7817223456021728682L
    private val id: Int? = null
    private val nickname: String? = null
    val identity: String? = null
    val number: Long? = null
    private val register_date: Date? = null
    val password: String? = null
}
