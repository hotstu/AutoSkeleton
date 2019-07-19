AutoSkeleton
===============
Yet Another library provides an easy way to show skeleton loading  like Facebook or Alipay with many Advantage than others

* 可预览 
在layout editor中可以直接看到效果

* 可拓展 
快速在任意类型的ViewGroup中实现本效果，适应各种场景

* kotlin 

* 代码简洁易懂

* 高性能 
使用shader绘制，不像[shimmerlayout](https://github.com/team-supercharge/ShimmerLayout)操作bitmap， 同时省去了遍历view tree 的draw过程)

* 支持自定义纹理
* 支持控制绘制的层级
* 支持过滤哪些view被绘制
* 支持动画
* 过渡自然
其他[替换view，替换reyclerView adapter的方案](https://github.com/ethanhua/Skeleton), 会出现画面抖动，或者出现与骨骼与真实view不一致的情况, 这里的过渡如丝般顺滑

TODO
自定义动画效果