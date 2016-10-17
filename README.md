#banya 瓣呀 介绍：
###优酷视频演示：
[点击观看app演示视频](http://v.youku.com/v_show/id_XMTc2MTY3Njg4OA==.html)


###项目介绍：
 - 整体采用material design 风格，本人是网易云音乐的粉丝，所以界面模仿了网页云音乐，另外，项目中尽量使用了5.0之后的新控件。

 
 - 项目整体采用mvp+rxjava+retrofit 框架，使用glide进行图片展示，用butterknif注解，另外使用了java 8 新特性，拉姆达表达式，安卓原生并不支持，需要导插件，具体见项目。


 - 并对retrofit的okhttpClient进行了缓存配置，很遗憾，豆瓣API在服务端并没有对返回数据进行Cache-Control 设置。
 
 - 后续会采用本地数据库对数据进行缓存，初步定为使用realm。
         
 - 项目地址：https://github.com/forezp/banya

 -  这是我的一个利用课余时间做的一个项目，仅供娱乐，项目的一切数据来自豆瓣api v2.0，一切数据归豆瓣所有。
 -  star一下吧，拜托了。


###主界面：

 - 主界面采用material design 设计风格，使用了NavigationView和DrawerLayout的抽屉效果，CoordinatorLayout和viewpager 配合，使用behavior属性，对toolbar的显示和隐藏进行了控制。使用了tablayout和viewpager配合，切换fragment，整体风格类似于网易云音乐。
 - 用到的豆瓣API有热映榜、top250、搜索图书、搜索音乐，等。
 
<img src="http://img.blog.csdn.net/20161015215329740" width = "300"  alt="图片名称" align=center /> <img src="http://img.blog.csdn.net/20161015215510459" width = "300"  alt="图片名称" align=center />
<img src="http://img.blog.csdn.net/20161015215731147" width = "300"  alt="图片名称" align=center /> <img src="http://img.blog.csdn.net/20161015215921087" width = "300"  alt="图片名称" align=center /> 

 
### 抽屉界面：
 
 抽屉界面使用到的icon来自google 的开源icon库，material design icon 地址：[点击进入](https://design.google.com/icons/index.html) ，看来谷歌为material design 花费了大量的精力，然而，一般的项目组都是ios的设计风格，让android去开发，作为一名安卓开发者，感觉到非常的不幸，为什么好的东西不能被产品和设计接受。
 
<img src="http://img.blog.csdn.net/20161015220007320" width = "300"  alt="图片名称" align=center /> 

### 项目中使用了webview 进行展示
 <img src="http://img.blog.csdn.net/20161015215807664" width = "300"  alt="图片名称" align=center /> 
### 感谢

 - 感谢豆瓣开放测试api。
 - 感谢开源项目  [gank.io](http://gank.io/api)
 - 同时项目也借鉴了其他的开源项目，感谢。
 - 同时希望可以帮助到其他人。
 
### 关于我
见图： 希望能认识一些趣味相投的人一起进步

 <img src="http://img.blog.csdn.net/20161015215702959" width = "300"  alt="图片名称" align=center />
 
 
### Contatct  Me:

 - Email:124746406@qq.com
 - Blog : [csdn blog](http://blog.csdn.net/forezp)
 - GitHub: [Forezp](https://github.com/forezp)
 

