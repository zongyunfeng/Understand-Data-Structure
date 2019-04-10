package me.yunfeng.exception

import me.yunfeng.util.getInvalidIndexMsg
import java.lang.IndexOutOfBoundsException

class VectorIndexOutOfBoundsException(index: Int, size: Int) :
    IndexOutOfBoundsException(getInvalidIndexMsg(index, size))