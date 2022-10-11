package xyz.flappy.statusmanager

/**
 * @Author: luweiming
 * @Description:页面状态定义
 * @Date: Created in 23:02 2022/9/21
 */
sealed class Status(layoutId: Int) {
    /**
     * 加载中
     * @property layoutId Int
     * @constructor
     */
    data class LOADING(val layoutId: Int) : Status(layoutId)


    data class CONTENT(val contentView:Int):Status(contentView)
    /**
     * 数据为空
     * @property layoutId Int
     * @constructor
     */
    data class EMPTY(val layoutId: Int) : Status(layoutId)

    /**
     * 数据异常或者网络异常
     * @property layoutId Int
     * @property click Function0<Unit>?
     * @constructor
     */
    data class ERROR(val layoutId: Int, val click: ((Int) -> Unit)? = null):Status(layoutId)

}
