package org.sbk.trie

class Trie(var root: Node, var size: Int, var endOfTheWord: Boolean) {

    constructor() : this(Node(), 0, false)

    fun insert(key: String) {
        if (key.isBlank()) {
            return
        }
        var currentNode = root
        var currentWord = key
        while (currentWord.isNotEmpty()) {
            val c: Char = currentWord[0]
            if(c in currentNode.children) {
                currentNode = currentNode.children[c]!!
            } else {
                val newNode = Node(c)
                currentNode.children[c] = newNode
                currentNode = newNode
            }
            currentWord = currentWord.substring(1)
        }
        currentNode.endOfTheWord = true
    }

    fun getAllWords(): List<String> {
        val allWords: MutableList<String> = mutableListOf()
        getAllWords("", root, allWords)
        return allWords
    }

    private fun getAllWords(startWord: String, node: Node, allWords: MutableList<String>) {
        for ((k, v) in node.children) {
            val newStartWord = startWord + k
            if (v.endOfTheWord ){
                allWords.add(newStartWord)
            }
            getAllWords(newStartWord, v, allWords)
        }
    }

}

class Node(var key: Char?, var children: MutableMap<Char, Node>, var endOfTheWord: Boolean ) {
    constructor(key:Char) : this(key, mutableMapOf<Char, Node>(), false)
    constructor() : this(null, mutableMapOf<Char, Node>(), false)
}