package github.hotstu.autoskeleton

import androidx.annotation.IntDef

/**
 * @author hglf [hglf](https://github.com/hotstu)
 * @desc
 * @since 7/19/19
 */
const val STYLE_DUMB_GREY = 0
const val STYLE_SHIMMER_GREY = 1
const val STYLE_LOLIPOP_RADIAL = 2

@IntDef(STYLE_DUMB_GREY, STYLE_SHIMMER_GREY, STYLE_LOLIPOP_RADIAL)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class StyleType

