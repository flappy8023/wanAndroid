package xyz.flappy.statusmanager

import android.view.ViewGroup

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:06 2022/9/22
 */
class Builder {
    var container: ViewGroup? = null
    var contentLayout: Int = -1
    var noDataLayout: Int = -1
    var loadingLayout: Int = -1
    var errorLayout: Int = -1
    fun build(): StatusManager = StatusManager(this)
}