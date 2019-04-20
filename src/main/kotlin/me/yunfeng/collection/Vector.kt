package me.yunfeng.collection

import me.yunfeng.constant.INCREMENT_SIZE
import me.yunfeng.exception.VectorIndexOutOfBoundsException
import java.util.*

@Suppress("UNCHECKED_CAST")
class Vector<E> constructor(capacity: Int = 0) : List<E> {
    //实际保存数据的数组
    private var elements: Array<Any?>
    //记录已存储的数据数量
    private var size = 0

    init {
        elements = when {
            capacity == 0 -> emptyArray()
            capacity > 0 -> arrayOfNulls(capacity)
            else -> throw IllegalArgumentException("invalid capacity:$capacity")
        }
    }

    //在在数组最后一个数据后添加一个元素
    fun add(element: E) {
        if (size == elements.size) {
            val newArray: Array<Any?> =
                arrayOfNulls(newCapacity())
            System.arraycopy(elements, 0, newArray, 0, size)
            elements = newArray
        }
        elements[size++] = element
    }

    //在指定位置添加一个元素
    fun add(index: Int, element: E) {
        validateElementIndex(index)
        if (size == elements.size) {
            val newArray: Array<Any?> =
                arrayOfNulls(newCapacity())
            System.arraycopy(elements, 0, newArray, 0, index)
            System.arraycopy(elements, index, newArray, index + 1, size - index)
            elements = newArray
        }
        elements[index] = element
        size++
    }

    //移除指定位置的元素
    fun remove(index: Int): E {
        validatePositionIndex(index)
        val oldVlue = elements[index]
        val numMoved = size - index - 1
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index, numMoved)
        elements[--size] = null
        return oldVlue as E
    }

    //移除特定元素
    fun remove(element: E): Boolean {
        val index = indexOf(element)
        return if (index >= 0) {
            remove(index)
            true
        } else {
            false
        }
    }

    //返回特定元素的索引
    fun indexOf(element: E): Int {
        var index = -1
        for (i in 0 until size) {
            index++
            if (elements[i] == element) {
                return index
            }
        }
        return -1
    }

    operator fun contains(element: E): Boolean {
        return indexOf(element) > 0
    }

    operator fun get(index: Int): E {
        validatePositionIndex(index)
        return elements[index] as E
    }

    operator fun set(index: Int, element: E) {
        validatePositionIndex(index)
        elements[index] = element
    }

    private fun validatePositionIndex(index: Int) {
        if (index < 0 || index >= size)
            throw VectorIndexOutOfBoundsException(index, size)
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index > size)
            throw VectorIndexOutOfBoundsException(index, size)
    }

    private fun newCapacity() = size + if (elements.size < INCREMENT_SIZE) INCREMENT_SIZE else INCREMENT_SIZE shr 1

    override fun toString(): String {
        return Arrays.toString(elements.copyOf(size))
    }
}