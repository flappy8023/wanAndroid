package xyz.flappy.statusmanager

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:13 2022/9/22
 */
class StatusManager(builder: Builder) {


    private val content: Status = Status.CONTENT(builder.contentLayout)
    private val error: Status = Status.ERROR(builder.errorLayout)
    private val empty: Status = Status.EMPTY(builder.noDataLayout)
    private val loading: Status = Status.LOADING(builder.loadingLayout)

    companion object {
        fun builder(): Builder = Builder()
    }

}