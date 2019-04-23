package me.yunfeng.collection

import me.yunfeng.exception.VectorIndexOutOfBoundsException

@Suppress("UNCHECKED_CAST")
class SingularLinkedList<E> : List<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    fun indexOf(element: E): Int {
        var index = 0
        var current = head
        while (current != null) {
            if (current == element) {
                return index
            }
            index++
            current = current.nextNode
        }
        return -1
    }

    fun add(element: E) {
        if (size == 0) {
            head = Node(element, null)
            tail = head
        } else {
            val previous = tail
            tail = Node(element, null)
            previous?.nextNode = tail
        }
        size++
    }

    fun add(index: Int, element: E) {
        validatePositionIndex(index)
        if (index == size) {
            add(element)
        } else {
            var current = head
            for (e in 0 until index - 1) {
                current = current?.nextNode
            }
            val next = current?.nextNode
            val newNode = Node(element, next)
            current?.nextNode = newNode
            size++
        }
    }

    fun remove(index: Int): E {
        validateElementIndex(index)
        var current = head
        for (i in 0 until index - 1) {
            current = current?.nextNode
        }
        val oldValue = current?.nextNode
        current?.nextNode = oldValue?.nextNode
        oldValue?.nextNode = null
        size--
        return oldValue as E
    }

    fun remove(element: E): Boolean {
        var current = head
        while (current != null) {
            if (current.element == element) {
                val next = current.nextNode
                val previous = getPrevious(current)

                if (previous == null) {
                    head = next
                } else {
                    previous.nextNode = next
                }

                if (next == null) {
                    tail = previous
                    previous?.nextNode = null
                } else {
                    previous?.nextNode = next
                }

                current.nextNode=null

                size--
                return true
            }
            current = current.nextNode
        }
        return false
    }

    operator fun contains(element: E): Boolean = indexOf(element) > 0

    operator fun get(index: Int): E {
        var current = head
        for (i in 0 until index) {
            current = current?.nextNode
        }
        return current?.element as E
    }

    operator fun set(index: Int, element: E) {
        validateElementIndex(index)
        var current = head
        for (i in 0 until index) {
            current = current?.nextNode
        }
        current?.element = element
    }

    private fun getPrevious(node: Node<E>?): Node<E>? {
        if (head != null && node == head) {
            return null
        }
        var current = head
        while (current != null) {
            if (current.nextNode == node) {
                return current
            }
            current = current.nextNode
        }
        return null
    }

    private fun validatePositionIndex(index: Int) {
        if (index < 0 || index > size)
            throw VectorIndexOutOfBoundsException(index, size)
    }

    private fun validateElementIndex(index: Int) {
        if (index < 0 || index >= size)
            throw VectorIndexOutOfBoundsException(index, size)
    }

    internal class Node<E> constructor(
        internal var element: E,
        internal var nextNode: Node<E>?
    )
}