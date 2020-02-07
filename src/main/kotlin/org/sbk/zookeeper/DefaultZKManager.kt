package org.sbk.zookeeper

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.ZooDefs
import org.apache.zookeeper.ZooKeeper
import org.sbk.config.Config
import org.sbk.config.ZookeeperConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import java.util.*

class DefaultZKManager : ZKManager {

    private val log: Logger

    private val zkeeper: ZooKeeper

    private val zkeeperConfig: ZookeeperConfig

    init {
        zkeeperConfig = Config.zConfig()
        zkeeper = ZKConnection(zkeeperConfig.host + ":" + zkeeperConfig.port).connect()!!
        log = LoggerFactory.getLogger(this.javaClass.name)
    }

    override fun create() {
        val path = zkeeperConfig.service + "/" + UUID.randomUUID()
        create(path, ByteArray(0))
    }

    override fun create(path: String, data: ByteArray) {
        log.info("Creating znode with path [{}]", path)
        zkeeper.create(
            path,
            data,
            ZooDefs.Ids.OPEN_ACL_UNSAFE,
            CreateMode.EPHEMERAL
        );
    }

    override fun getZNodeData(path: String, watchFlag: Boolean): Any {
        log.info("Get data from path [{}]", path)
        val b: ByteArray = zkeeper.getData(path, null, null)
        return String(b, Charset.forName("UTF-8"))
    }

    override fun update(path: String, data: ByteArray) {
        val version = zkeeper.exists(path, true).version
        zkeeper.setData(path, data, version)
    }
}
