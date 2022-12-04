package com.flappy.wanandroid.data.model

import android.os.Parcel
import android.os.Parcelable
import com.flappy.wanandroid.MyApp
import com.flappy.wanandroid.R

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:55 2022/11/10
 */

/**
 *
 *
 * @property completeDate
 * @property completeDateStr
 * @property content
 * @property date
 * @property dateStr
 * @property id
 * @property priority
 * @property status 状态， 1-完成；0未完成; 默认全部展示；
 * @property title
 * @property type
 * @property userId
 */
data class Todo(
    val completeDate: Long?,
    val completeDateStr: String?,
    var content: String,
    val date: Long,
    var dateStr: String,
    val id: Long,
    val priority: Int,
    val status: Int,
    var title: String,
    val type: Int,
    val userId: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(completeDate)
        parcel.writeString(completeDateStr)
        parcel.writeString(content)
        parcel.writeLong(date)
        parcel.writeString(dateStr)
        parcel.writeLong(id)
        parcel.writeInt(priority)
        parcel.writeInt(status)
        parcel.writeString(title)
        parcel.writeInt(type)
        parcel.writeLong(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }
}

const val TODO_TYPE_JUST_THIS = 0
const val TODO_TYPE_WORK = 1
const val TODO_TYPE_STUDY = 2
const val TODO_TYPE_LIFE = 3

val Todo.typeDesc: String
    get() = when (this.type) {
        TODO_TYPE_JUST_THIS -> MyApp.app.getString(R.string.todo_type_all)
        TODO_TYPE_WORK -> MyApp.app.getString(R.string.todo_type_work)
        TODO_TYPE_STUDY -> MyApp.app.getString(R.string.todo_type_study)
        else -> MyApp.app.getString(R.string.todo_type_life)
    }