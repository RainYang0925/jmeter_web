# Jmeter Web项目使用指南

- 项目的内网访问地址：http://10.2.250.202:9099/JmeterWEB/
- 打开链接你会看到，如下界面（请大家尽量舒勇google chrome浏览器）：
![image](http://of9xsczb1.bkt.clouddn.com/JMeterWEB1.png)
- 在界面中选择对应的选项卡：（目前只支持HTTP模板，自定义脚本上传，测试相应结果两个选项卡），HTTP模板是根据页面选择的参数生成jmx文件，自定义脚本是用户直接上传jmx脚本。
- 下图是执行脚本的页面，在页面中可以选择在本地执行与在远程机执行（远程机执行是指在3台机器上同步执行脚本，比如你的脚本是10个线程，选择两台远程机与加上本机就相当于执行30个线程）。其他两台远程机器的IP是10.2.250.203:1099，10.2.250.204:1099。
![image](http://of9xsczb1.bkt.clouddn.com/JMeterWEB2.png)
- 生成的测试报告如下图所示。
![image](http://of9xsczb1.bkt.clouddn.com/JMeterWEB3.png)
- 查看Response，request的数据
![image](http://of9xsczb1.bkt.clouddn.com/JMeterWEB4.png)

- JMeter3.0提供一个用于生成HTML页面格式图形化报告的扩展模块。该模块支持通过两种方式生成多维度图形化测试报告：在JMeter性能测试结束时，自动生成本次测试的HTML图形化报告使用一个已有的结果文件(如CSV文件)来生成该次结果的HTML图形化报告
其默认提供的度量维度包括：
1. APDEX(Application Performance Index)指数
2.	聚合报告  
类似于UI上的Aggregate Report

3. Errors报告  
展示不同错误类型的数量以及百分比
4. 响应时间变化曲线
展示平均响应时间随时间变化情况  
类似于JMeter Plugins在UI上的jp@gc - Response Times Over Time
5. 数据吞吐量时间曲线  
展示每秒数据吞吐量随时间变化的情况
类似于JMeter Plugins在UI上的jp@gc - Bytes Throughput Over Time
6. Latency time变化曲线  
展示Latency time随时间变化的情况  
类似于JMeter Plugins在UI上的jp@gc - Response Latencies Over Time
7. 每秒点击数曲线  
类似于JMeter Plugins在UI上的jp@gc - Hits per Second
8. HTTP状态码时间分布曲线  
展示响应状态码随时间的分布情况  
类似于JMeter Plugins在UI上的jp@gc - Response Codes per Second
9. 事务吞吐量时间曲线(TPS)  
10. 展示每秒处理的事务数随时间变化情况  
类似于JMeter Plugins在UI上的jp@gc - Transactions per Second
11. 平均响应时间与每秒请求数的关系图  
展示平均响应时间与每秒请求数(可以理解为QPS)的关系
12. Latency time与每秒请求数的关系图  
展示Latency time与每秒请求数的关系  
13. 响应时间百分位图  
响应时间的百分位分布图
14. 活动线程数变化曲线  
展示测试过程中活动线程数随时间变化情况
15. 平均响应时间与线程数的关系图  
展示平均响应时间与线程数的关系
类似于JMeter Plugins在UI上的jp@gc - Response Times vs Threads
16. 柱状响应时间分布图  
展示落在各个平均响应时间区间的请求数情况
