package top.kagerou.langbao.service

import org.springframework.stereotype.Service

@Service
class SystemService {
     open fun getSysInfo(): Any{
         return "info"
     }
}
