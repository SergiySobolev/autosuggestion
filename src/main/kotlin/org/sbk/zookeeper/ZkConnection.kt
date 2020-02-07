package org.sbk.zookeeper

import org.apache.zookeeper.Watcher
import org.apache.zookeeper.Watcher.Event.KeeperState
import org.apache.zookeeper.ZooKeeper
import java.io.IOException
import java.util.concurrent.CountDownLatch


class ZKConnection(var host:String) {

    private val connectionLatch = CountDownLatch(1)

    @Throws(IOException::class, InterruptedException::class)
    fun connect(): ZooKeeper? {
        val zoo = ZooKeeper(this.host, 2000, Watcher { we ->
            if (we.state == KeeperState.SyncConnected) {
                connectionLatch.countDown()
            }
        })
        connectionLatch.await()
        return zoo
    }

}