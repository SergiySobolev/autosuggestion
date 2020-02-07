package org.sbk.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable

class Config {

    companion object {
        fun appConfig(): AppConfig {
            return getConfig("application.yaml", AppConfig.serializer())
        }

        fun zConfig(): ZookeeperConfig {
            return getConfig("zookeeper.yaml", ZookeeperConfig.serializer())
        }

        private fun <T> getConfig(resource: String, deserializationStrategy: DeserializationStrategy<T>): T {
            return Yaml.default.parse(
                deserializationStrategy,
                Config::javaClass.javaClass.classLoader.getResourceAsStream(resource)!!.bufferedReader().use { it.readText() }
            )
        }

    }

}


@Serializable
data class AppConfig(val port: Int)

@Serializable
data class ZookeeperConfig(val host: String, val port: String, val service: String)