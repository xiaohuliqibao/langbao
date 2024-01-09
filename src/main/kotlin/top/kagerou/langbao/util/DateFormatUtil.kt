import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatUtil {
    fun test(){
        println("test")
    }

    fun simpleDate(date: String): LocalDate {
        var ofPattern = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(date,ofPattern)
    }
}