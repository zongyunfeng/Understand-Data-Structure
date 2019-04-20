package me.yunfeng.collection

class SingularLinkedList<E>:List<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    internal class Node<E> constructor(internal var element: E, internal var nextNode: Node<E>?)
}