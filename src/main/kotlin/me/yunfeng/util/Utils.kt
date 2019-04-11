package me.yunfeng.util

import me.yunfeng.collection.Vector

fun getInvalidIndexMsg(index: Int, size: Int) = "invalid index $index,size is $size "

fun <T> vectorOf(vararg param: T): Vector<T> {
    val vector = Vector<T>()
    param.forEach {
        vector.add(it)
    }
    return vector
}