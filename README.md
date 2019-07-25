[![author](https://img.shields.io/badge/author-hglf-blue.svg)](https://github.com/hotstu)
[![Download](https://api.bintray.com/packages/hglf/maven/AutoSkeleton/images/download.svg) ](https://bintray.com/hglf/maven/AutoSkeleton/_latestVersion)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


AutoSkeleton
===============
Yet Another skeleton loading library provides an easy way to show skeleton loading like Facebook or Alipay aiming performance and extensibility
[中文](/README_CN.md)

### use
 ```groovy

    implementation 'github.hotstu.autoskeleton:autoskeleton:1.0.0'

 ```
### features

* preview in android studio 

  you can see the preview  in layout editor directly in android studio

* extensibility

  AutoSkeleton is code in the 'Delegate pattern' that means it's not limited to a specific type of ViewGroup
  
  you can simply extend any ViewGroup to implement the skeleton loading ability by delegate some method to the `SkeletonDelegate` class

* kotlin 

  written in pure kotlin except some java code to show interoperability with java

* better performance

unlike [ShimmerLayout][1] which traversal the view tree  and draw all the child on a bitmap twice, AutoSkeleton does not call `super.dispatchDraw(canvas)`; 
when enabled

* custom drawing
AutoSkeleton is designed as a loose developers have the full control of the drawing steps such as
  * control the traversal view tree depth
  * Exclude some views by view depth & view properties
  * custom animation
 
### similar Libraries
[ShimmerLayout][1]
[Skeleton][2]

### THANKS

this library is inspired by [JakeWharton/scalpel](https://github.com/JakeWharton/scalpel)



[1]: https://github.com/team-supercharge/ShimmerLayout
[2]: https://github.com/ethanhua/Skeleton