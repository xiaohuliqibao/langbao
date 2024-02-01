package top.kagerou.langbao.util

class DstConstant {
    companion object{
        const val INSTALL_DST = "install.sh"
        const val DST_START: String = "dstStart.sh"

        /**
         * 启动脚本的存放路径 ~/dst/bin
         */
        const val START_DST_BIN_PATH: String = "/dst/bin"

        /**
         * 全局的地面进程、存档的名称
         */
        const val DST_MASTER: String = "Master"

        /**
         * 全局的洞穴进程、存档的名称
         */
        const val DST_CAVES: String = "Caves"

        /**
         * 地面的screen任务的名称 DST_MASTER
         */
        const val SCREEN_WORK_MASTER_NAME: String = "DST_MASTER"

        /**
         * 洞穴的screen任务的名称 DST_CAVES
         */
        const val SCREEN_WORK_CAVES_NAME: String = "DST_CAVES"

        /**
         * 启动地面进程命令 设置名称为 DST_MASTER
         */
        const val START_MASTER_CMD: String =
            "cd ~/dst/bin/ ; screen -d -m -S \"$SCREEN_WORK_MASTER_NAME\"  ./dontstarve_dedicated_server_nullrenderer -console -cluster MyDediServer -shard $DST_MASTER  ;"

        /**
         * 启动洞穴进程命令 设置名称为 DST_CAVES
         */
        const val START_CAVES_CMD: String =
            "cd ~/dst/bin/ ; screen -d -m -S \"$SCREEN_WORK_CAVES_NAME\"  ./dontstarve_dedicated_server_nullrenderer -console -cluster MyDediServer -shard $DST_CAVES ;"

        /**
         * 检查目前所有的screen作业，并删除已经无法使用的screen作业
         */
        const val CLEAR_SCREEN_CMD: String = "screen -wipe "

        /**
         * 查询地面进程号命令
         */
        const val FIND_MASTER_CMD: String = " ps -ef | grep -v grep |grep '$DST_MASTER'|sed -n '1P'|awk '{print $2}' "

        /**
         * 查询洞穴进程号命令
         */
        const val FIND_CAVES_CMD: String = " ps -ef | grep -v grep |grep '$DST_CAVES'|sed -n '1P'|awk '{print $2}' "

        /**
         * 杀死地面进程命令
         */
        const val STOP_MASTER_CMD: String =
            "ps -ef | grep -v grep |grep '$DST_MASTER' |sed -n '1P'|awk '{print $2}' |xargs kill -9"

        /**
         * 杀死洞穴进程命令
         */
        const val STOP_CAVES_CMD: String =
            "ps -ef | grep -v grep |grep '$DST_CAVES' |sed -n '1P'|awk '{print $2}' |xargs kill -9"

        /**
         * 更新游戏目录
         */
        const val UPDATE_GAME_CMD: String =
            "cd ~/steamcmd ; ./steamcmd.sh +login anonymous +force_install_dir ~/dst +app_update 343050 validate +quit"

        /**
         * 删除地面游戏记录
         */
        const val DEL_RECORD_MASTER_CMD: String = "rm -r ~/.klei/DoNotStarveTogether/MyDediServer/$DST_MASTER/save"

        /**
         * 删除地面游戏记录
         */
        const val DEL_RECORD_CAVES_CMD: String = "rm -r ~/.klei/DoNotStarveTogether/MyDediServer/$DST_CAVES/save"

        /**
         * 获取地面的玩家 替换99999999关键字
         */
        const val MASTER_PLAYLIST_CMD: String =
            "screen -S \"$SCREEN_WORK_MASTER_NAME\" -p 0 -X stuff \"for i, v in ipairs(TheNet:GetClientTable()) do  print(string.format(\\\"playerlist %s [%d] %s %s %s\\\", 99999999, i-1, v.userid, v.name, v.prefab )) end$(printf \\\\r)\"\n"

        /**
         * 饥荒的启动程序
         */
        const val DST_START_PROGRAM: String = "dontstarve_dedicated_server_nullrenderer"

        /**
         * 单斜杠
         */
        const val SINGLE_SLASH: String = "/"

        /**
         * 备份的存档文件的扩展名
         */
        const val BACKUP_FILE_EXTENSION: String = ".tar"

        /**
         * 备份的存档文件的扩展名
         */
        const val BACKUP_FILE_EXTENSION_NON_POINT: String = "tar"

        /**
         * 备份的存档文件的扩展名zip
         */
        const val BACKUP_FILE_EXTENSION_NON_POINT_ZIP: String = "zip"

        /**
         * 不允许下载文件路径中存在改字符
         */
        const val BACKUP_ERROR_PATH: String = "../"

        /**
         * 游戏文档
         */
        const val DST_DOC_PATH: String = ".klei/DoNotStarveTogether"

        /**
         * 饥荒游戏用户存档位置
         */
        const val DST_USER_GAME_CONFG_PATH: String = "/.klei/DoNotStarveTogether/MyDediServer"

        /**
         * 饥荒游戏存档路径
         */
        const val DST_USER_SAVE_FILE: String = "save"

        /**
         * 游戏配置的名称
         */
        const val DST_USER_SERVER_INI_NAME: String = "server.ini"

        /**
         * 游戏房间设置的文件名称
         */
        const val DST_USER_CLUSTER_INI_NAME: String = "cluster.ini"

        /**
         * token设置文件
         */
        const val DST_USER_CLUSTER_TOKEN: String = "cluster_token.txt"

        /**
         * 地上mod保存地址
         */
        const val DST_USER_GAME_MASTER_MOD_PATH: String =
            ".klei/DoNotStarveTogether/MyDediServer/$DST_MASTER/modoverrides.lua"

        /**
         * 洞穴mod保存位置
         */
        const val DST_USER_GAME_CAVES_MOD_PATH: String =
            ".klei/DoNotStarveTogether/MyDediServer/$DST_CAVES/modoverrides.lua"

        /**
         * 地面地图配置地址
         */
        const val DST_USER_GAME_MASTER_MAP_PATH: String =
            ".klei/DoNotStarveTogether/MyDediServer/$DST_MASTER/leveldataoverride.lua"

        /**
         * 洞穴地图配置地址
         */
        const val DST_USER_GAME_CAVES_MAP_PATH: String =
            ".klei/DoNotStarveTogether/MyDediServer/$DST_CAVES/leveldataoverride.lua"

        /**
         * 游戏配置文件
         */
        const val DST_USER_GAME_CONFIG_PATH: String = ".klei/DoNotStarveTogether/MyDediServer/cluster.ini"

        /**
         * 地面游戏运行日志位置
         */
        const val DST_MASTER_SERVER_LOG_PATH: String =
            ".klei/DoNotStarveTogether/MyDediServer/$DST_MASTER/server_log.txt"

        /**
         * 地面用户聊天信息
         */
        const val DST_MASTER_SERVER_CHAT_LOG_PATH: String =
            ".klei/DoNotStarveTogether/MyDediServer/$DST_MASTER/server_chat_log.txt"

        /**
         * 洞穴游戏运行日志位置
         */
        const val DST_CAVES_SERVER_LOG_PATH: String = ".klei/DoNotStarveTogether/MyDediServer/$DST_CAVES/server_log.txt"

        /**
         * 管理员存储位置
         */
        const val DST_ADMIN_LIST_PATH: String = ".klei/DoNotStarveTogether/MyDediServer/adminlist.txt"

        /**
         * 黑名单存储位置
         */
        const val DST_PLAYER_BLOCK_LIST_PATH: String = ".klei/DoNotStarveTogether/MyDediServer/blocklist.txt"

        /**
         * 游戏mod设置
         */
        const val DST_MOD_SETTING_PATH: String = "dst/mods/dedicated_server_mods_setup.lua"

        /**
         * master的session目录
         */
        const val DST_USER_GAME_MASTER_SESSION: String = "$DST_MASTER/save/session"

        /**
         * 脚本目录名称 shell
         */
        val SHELL_FILE_NAME: String? = null

        /**
         * 脚本绝对路径 /Users/qinming/Documents/project/dst-admin/shell
         */
        val SHELL_FILE_PATH: String? = null

        /**
         * 脚本的项目存放位置
         */
        const val SHELL_PROJECT_PATH: String = "shell/"

        /**
         * 项目的数据
         */
        const val DST_ADMIN_JSON: String = ".dst_admin_db.json"

        /**
         * 系统根目录mac:/Users/qinming , ubuntu: /home/ubuntu ,游戏将按照在该目录下
         */
        val ROOT_PATH: String? = null
    }
}