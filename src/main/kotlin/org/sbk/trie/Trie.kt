package org.sbk.trie

interface Trie {
    /**
     * Insert [key] to the trie.
     */
    fun insert(key: String)

    /**
     * Search all words start from [prefix].
     * @return all words start from [prefix]
     */
    fun getAllWordsByPrefix(prefix: String): List<String>

    /**
     * @return all words
     */
    fun getAllWords(): List<String>
}