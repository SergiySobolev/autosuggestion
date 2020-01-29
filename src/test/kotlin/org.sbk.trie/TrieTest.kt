package org.sbk.trie

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TrieTest {

    @Test
    @DisplayName("Newly created trie should have zero size")
    fun test_sizeShouldBeZero_ifTrieIsEmpty() {
        val trie = Trie()
        assertEquals(trie.size, 0)
    }

    @Test
    @DisplayName("Trie should containt 'first' and 'second' words")
    fun test_shouldContainTwoWords_whenTwoWordsInserted() {
        val trie = Trie()
        trie.insert("")
        trie.insert("second")
        trie.insert("first")
        val allWords = trie.getAllWords()
        assertAll("allWords",
            {assertThat(allWords, hasSize(equalTo(2)))},
            {assertThat(allWords, hasItem("first"))},
            {assertThat(allWords, hasItem("second"))}
        )
    }


}