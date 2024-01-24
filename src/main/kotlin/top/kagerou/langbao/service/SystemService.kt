package top.kagerou.langbao.service

import org.springframework.stereotype.Service
import top.kagerou.langbao.entity.SystemInfomation

@Service
class SystemService {
     open fun getSysInfo(): SystemInfomation{
         val server = SystemInfomation()
         server.copyTo()
         return server
     }
}
