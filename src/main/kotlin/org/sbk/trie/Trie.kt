package org.sbk.trie

class Trie(var root: Node, var size: Int) {

    constructor() : this(Node(), 0)



}

class Node {
    var key = null
    var value = null
    var children = emptyMap<Char, Node>()
}