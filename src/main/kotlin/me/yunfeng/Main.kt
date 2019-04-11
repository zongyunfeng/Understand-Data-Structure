package me.yunfeng

import me.yunfeng.util.vectorOf

fun main() {
    val vector = vectorOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
    println(vector)
    vector.remove(1)
    println(vector)
    vector.remove('5')
    println(vector)
    vector.add(7, 'a')
    println(vector)
    vector[7] = 'b'
    println(vector)
    println(vector.indexOf('9'))
    println(vector.contains('c'))
}