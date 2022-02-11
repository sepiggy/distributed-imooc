#### Mac

自带的apache中的ab是有最大并发限制的，想要突破限制，要重新下载一个apache并且编译
https://www.cnblogs.com/cjsblog/archive/2019/03/10/10506647.html

#### Windows下安装ab

https://www.apachelounge.com/download/

快速下载 [httpd-2.4.46-win32](https://imcfile.oss-cn-beijing.aliyuncs.com/shizhan/file/liaoshixiong/httpd-2.4.46-win32-VS16.zip)

快速下载 [httpd-2.4.46-win64](https://imcfile.oss-cn-beijing.aliyuncs.com/shizhan/file/liaoshixiong/httpd-2.4.46-win64-VS16.zip)

#### 使用

```
ab -n 100 -c 10 http://www.imooc.com/
```