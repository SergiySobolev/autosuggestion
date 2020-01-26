package org.sbk.trie

import kotlin.test.*

class TrieTest {

    @Test
    fun test_sizeShouldBeZero_ifTrieIsEmpty() {
        val trie = Trie()
        assertEquals(trie.size, 0)
    }

}