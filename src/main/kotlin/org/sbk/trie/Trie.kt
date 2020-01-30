package org.sbk.trie

import java.lang.IllegalStateException

class Trie private constructor (var root: Node, var size: Int, var endOfTheWord: Boolean) {

    private constructor() : this(Node(), 0, false)

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
        size += 1
    }

    fun getAllWordsByPrefix(prefix: String): List<String> {
        val words = mutableListOf<String>()
        if (prefix.isBlank()) {
            return words
        }
        val prefixTail: Node? = getPrefixTail(prefix)
        if (prefixTail == null) {
            return words
        } else {
            findWordsStartFrom(prefixTail, prefix, words)
        }
        return words
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

    private fun getPrefixTail(prefix: String): Node? {
        var currentNode = root
        for (c:Char in prefix) {
            if(currentNode.get(c) == null) {
                return null
            }
            currentNode = currentNode.get(c) ?: throw IllegalStateException("")
        }
        return currentNode
    }


    private fun findWordsStartFrom(prefixTail: Node, prefix: String, words: MutableList<String>) {
        for ((k,v) in prefixTail.children) {
            val newPrefix = prefix + k
            if (v.endOfTheWord) {
                words.add(newPrefix)
            }
            findWordsStartFrom(v, newPrefix, words)
        }
    }



    companion object {
        fun createEmptyTrie() : Trie = Trie()
    }

}

class Node(var key: Char?, var children: MutableMap<Char, Node>, var endOfTheWord: Boolean ) {
    constructor(key:Char) : this(key, mutableMapOf<Char, Node>(), false)
    constructor() : this(null, mutableMapOf<Char, Node>(), false)
    fun get(key: Char): Node? {
       return children[key]
    }
}