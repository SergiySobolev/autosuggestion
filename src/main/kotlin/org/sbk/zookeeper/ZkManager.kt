package org.sbk.zookeeper

import org.apache.zookeeper.KeeperException


interface ZKManager {

    @Throws(KeeperException::class, InterruptedException::class)
    fun create()

    @Throws(KeeperException::class, InterruptedException::class)
    fun create(path: String, data: ByteArray)

    fun getZNodeData(path: String, watchFlag: Boolean): Any

    @Throws(KeeperException::class, InterruptedException::class)
    fun update(path: String, data: ByteArray)
}