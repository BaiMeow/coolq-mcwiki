package cn.miaoscraft.mcWiki

import net.mamoe.mirai.console.plugins.PluginBase
import net.mamoe.mirai.event.subscribeMessages
import java.net.URLEncoder

object PluginMain : PluginBase() {

    override fun onEnable() {
        super.onEnable()

        logger.info("Plugin loaded!")

        // 订阅所有消息（群消息、私聊消息、临时对话）
        subscribeMessages {

            // 始终触发，此外还有atBot、case、contains啥的，打个this. 就可以看ide的提示了  PS: this.其实可以省略，但是方便理解
            this.always {
                val msg = this.message.contentToString() // 把获取到的消息内容强制转换为String   至于为啥不用.toString()可以按住alt点左边的contentToString()看里面的注释
                val tmp = msg.split(" ") // 把消息内容用空格分开，得到一个String数组
                if (tmp[0] != "wiki"){
                    return@always // 如果数组第一条内容不是wiki的话就结束此次事件
                }
                reply(getURL(tmp[1])) // 不用考虑报错，如果错了的话会丢到mirai-console展示，对机器人无影响
            }
        }
    }

    fun getURL(keyword: String): String {
        return "官方Wiki:\n" +
                "https://minecraft-zh.gamepedia.com/index.php?search=" + URLEncoder.encode(keyword, "GBK") + "&title=Special:%E6%90%9C%E7%B4%A2&go=%E5%89%8D%E5%BE%80" + "\n" +
                "镜像Wiki:\n" +
                "https://searchwiki.biligame.com/mc/index.php?search=" + URLEncoder.encode(keyword, "GBK")
    }
}