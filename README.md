# RePluginDemo
这是关于RePlugin插件的基本配置使用
关于RePlugin的官方配置就不在述说
# 搭建插件步骤 
1新建一个放插件的文件夹（plugins）->2在工程中新建你要生成的moudle插件->3把整个moudle插件剪切到plugins文件夹下->4在settings.gradle文件中添加
要引用的插件include ':plugins:plugin_syssetting'->5在插件（plugin_syssetting）build.gradle中配置相关复制代码->6执行gradle命令生成相应的jar包

