package org.sbk.trie

interface Trie {
    /**
     * Insert [key] to the trie.
     */
    fun insert(key: String)

    /**
     * Check if [key] present in the trie.
     * @return true if [key] is present false otherwise
     */
    fun contains(key:String):Boolean

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