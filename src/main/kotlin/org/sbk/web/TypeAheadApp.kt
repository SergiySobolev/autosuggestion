package org.sbk.web

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.sbk.config.Config
import org.sbk.trie.DefaultTrie
import org.sbk.zookeeper.DefaultZKManager
import org.sbk.zookeeper.ZKManager

val trie = DefaultTrie.createEmptyTrie()

fun main() {
    val config = Config.appConfig()
    val zkManager:ZKManager = DefaultZKManager()

    zkManager.create()
    embeddedServer(Netty, port = config.port, module = Application::module).start()
}

fun Application.module() {

    routing {
        route("/key") {
            get("/{key}") {
                val key = call.parameters["key"]!!
                val contains:Boolean = trie.contains(key)
                if (contains) {
                    call.respondText(status = HttpStatusCode.OK) {"This key exists."}
                } else {
                    call.respondText(status = HttpStatusCode.NotFound) {"This key doesn't exist."}
                }
            }
            post("/{key}") {
                val key = call.parameters["key"]!!
                trie.insert(key)
                call.respondText(status = HttpStatusCode.Created) {"Key inserted."}
            }
            delete("/{key}") {
                val key = call.parameters["key"]!!
                trie.delete(key)
                call.respondText(status = HttpStatusCode.OK) {"Key deleted."}
            }
        }
        route("suggest") {
            get("/{key}") {
                val key = call.parameters["key"]!!
                val suggestion = trie.getAllWordsByPrefix(key)
                call.respondText(status = HttpStatusCode.OK) {suggestion.toString()}

            }
        }
    }
}



