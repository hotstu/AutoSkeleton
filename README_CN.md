[![author](https://img.shields.io/badge/author-hglf-blue.svg)](https://github.com/hotstu)
[![Download](https://api.bintray.com/packages/hglf/maven/AutoSkeleton/images/download.svg) ](https://bintray.com/hglf/maven/AutoSkeleton/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


AutoSkeleton
===============

类似Facebook和Alipay的骨架加载loading动画库

![demo1][demo1] ![demo1][demo2]

### 使用

 ```groovy

    implementation 'github.hotstu.autoskeleton:autoskeleton:1.0.0'

 ```
 
 ### 特色
 
* 可预览 
在layout editor中可以直接看到效果

* 可拓展 
快速在任意类型的ViewGroup中实现本效果，适应各种场景

* kotlin 

* 代码简洁易懂

* 高性能 
与[ShimmerLayout][1]遍历view tree 将调用所有子view的draw方法到一个bitmap上的方案不同,AutoSkeleton不调用
`super.dispatchDraw(canvas)`，性能大幅提升

* 可定制
  * 自由设置遍历的层级,排除无意义的布局
  * 可以根据层级和view属性筛选过滤哪些view需要绘制
  * 支持自定义纹理
  * 对单个子view自定义绘制过程
  * 支持动画设置Animator
  
  ![demo][demo]
  
* 过渡自然
不像[Skeleton][2]采用的替换view的方案, 会出现画面抖动，或者出现与骨骼与真实view不一致的情况,
 这里的过渡如丝般顺滑

[1]: https://github.com/team-supercharge/ShimmerLayout
[2]: https://github.com/ethanhua/Skeleton
[demo1]: /screenshots/demo1.gif
[demo2]: /screenshots/demo2.gif
[demo]: /screenshots/demo.gif

