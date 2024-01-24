package top.kagerou.langbao.entity

import oshi.SystemInfo
import oshi.hardware.CentralProcessor
import oshi.hardware.CentralProcessor.TickType
import oshi.hardware.GlobalMemory
import oshi.hardware.HardwareAbstractionLayer
import oshi.software.os.OSFileStore
import oshi.software.os.OperatingSystem
import oshi.util.Util
import top.kagerou.langbao.entity.systemInfo.Cpu
import top.kagerou.langbao.entity.systemInfo.Memo
import top.kagerou.langbao.entity.systemInfo.SysFile
import top.kagerou.langbao.entity.systemInfo.SysInfo
import top.kagerou.langbao.util.Arith


data class SystemInfomation(val cpu: Cpu = Cpu(), val memo: Memo = Memo(), val sysFiles: MutableList<SysFile> = mutableListOf()){

    private var OSHI_WAIT_SECOND: Long = 1000L
    fun copyTo() {
        val si = SystemInfo()
        val hardware: HardwareAbstractionLayer = si.hardware
        setCpuInfo(hardware.processor)
        setMemoryInfo(hardware.memory)
        setSysFile(si.operatingSystem)
    }

    /**
     * 设置CPU信息
     */
    private fun setCpuInfo(processor: CentralProcessor){
        val prevTicks: LongArray = processor.systemCpuLoadTicks
        Util.sleep(OSHI_WAIT_SECOND)
        val nowTicks: LongArray = processor.systemCpuLoadTicks
        val nice: Long = nowTicks[TickType.NICE.index] - prevTicks[TickType.NICE.index]
        val irq: Long = nowTicks[TickType.IRQ.index] - prevTicks[TickType.IRQ.index]
        val softIrq: Long = nowTicks[TickType.SOFTIRQ.index] - prevTicks[TickType.SOFTIRQ.index]
        val steal: Long = nowTicks[TickType.STEAL.index] - prevTicks[TickType.STEAL.index]
        val cSys: Long = nowTicks[TickType.SYSTEM.index] - prevTicks[TickType.SYSTEM.index]
        val user: Long = nowTicks[TickType.USER.index] - prevTicks[TickType.USER.index]
        val ioWait: Long = nowTicks[TickType.IOWAIT.index] - prevTicks[TickType.IOWAIT.index]
        val idle: Long = nowTicks[TickType.IDLE.index] - prevTicks[TickType.IDLE.index]
        val totalCpu = nice + idle + ioWait + irq + steal + softIrq + cSys + user
        cpu.cpuNum = processor.logicalProcessorCount
        cpu.total = totalCpu.toDouble()
        cpu.sys = cSys.toDouble()
        cpu.used = user.toDouble()
        cpu.wait = ioWait.toDouble()
        cpu.free = idle.toDouble()
    }

    /**
     * 设置内存信息
     */
    private fun setMemoryInfo(memory: GlobalMemory){
        memo.total = memory.total.toDouble()
        memo.free = memory.available.toDouble()
        memo.used = memory.total.toDouble() - memory.available.toDouble()
    }

    /**
     * 设置磁盘信息
     */

    private fun setSysFile(os: OperatingSystem){
        val fileSystem = os.fileSystem
        val fsArray = fileSystem.fileStores.toTypedArray<OSFileStore>()
        for (fs:OSFileStore in fsArray){
            val free = fs.usableSpace
            val total = fs.totalSpace
            val used = total - free
            val sysFile = SysFile()
            sysFile.dirName = fs.mount
            sysFile.sysTypeName = fs.type
            sysFile.typeName = fs.name
            sysFile.total = convertFileSize(total)
            sysFile.free = convertFileSize(free)
            sysFile.used = convertFileSize(used)
            sysFile.usage = Arith.mul(Arith.div(used.toDouble(),total.toDouble(),4),100.0)
            sysFiles.add(sysFile)
        }
    }
    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    private fun convertFileSize(size: Long): String {
        val kb: Long = 1024
        val mb = kb * 1024
        val gb = mb * 1024
        if (size >= gb) {
            return String.format("%.1f GB", size.toFloat() / gb)
        } else if (size >= mb) {
            val f = size.toFloat() / mb
            return String.format(if (f > 100) "%.0f MB" else "%.1f MB", f)
        } else if (size >= kb) {
            val f = size.toFloat() / kb
            return String.format(if (f > 100) "%.0f KB" else "%.1f KB", f)
        } else {
            return String.format("%d B", size)
        }
    }
}
