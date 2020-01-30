package org.sbk.trie

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class TrieTest {

    @Test
    @DisplayName("Empty created trie should have zero size")
    fun test_sizeShouldBeZero_ifTrieIsEmpty() {
        val trie = DefaultTrie.createEmptyTrie()
        assertEquals(trie.size, 0)
    }

    @Test
    @DisplayName("Trie should contain 'first' and 'second' words")
    fun test_shouldContainTwoWords_whenTwoWordsInserted() {
        val trie = DefaultTrie.createEmptyTrie()
        trie.insert("")
        trie.insert("second")
        trie.insert("first")
        trie.insert("se")
        val allWords = trie.getAllWords()
        assertAll("allWords",
            {assertThat(trie.size, equalTo(3))},
            {assertThat(allWords, hasSize(equalTo(3)))},
            {assertThat(allWords, hasItems("first", "second", "se"))}
        )
    }

    @Test
    @DisplayName("Trie should contain only inserted words")
    fun test_shouldContainWord_whenItIsInserted() {
        val trie = DefaultTrie.createEmptyTrie()
        trie.insert("someword")
        trie.insert("anotherword")
        assertAll(
            { assertTrue(trie.contains("someword"))},
            { assertTrue(trie.contains("anotherword"))},
            { assertFalse(trie.contains("unknownword"))},
            { assertFalse(trie.contains(""))}
        )
    }


    @Test
    @DisplayName("Search by prefix without result set limiting")
    fun test_getAllWordsByPrefix() {
        val trie = DefaultTrie.createEmptyTrie()
        trie.insert("crow")
        trie.insert("cat")
        trie.insert("car")
        trie.insert("cameo")
        trie.insert("howl")
        trie.insert("hollow")
        trie.insert("holy")
        trie.insert("hood")
        trie.insert("harvest")
        assertAll("Prefix word",
            {assertThat(trie.size, equalTo(9))},
            {assertThat(trie.getAllWordsByPrefix("c"), hasItems("crow", "cat", "cameo", "car"))},
            {assertThat(trie.getAllWordsByPrefix("ca"), hasItems("cat", "cameo", "car"))},
            {assertThat(trie.getAllWordsByPrefix("cm"), empty())},
            {assertThat(trie.getAllWordsByPrefix("h"), hasItems("howl", "hollow", "hood", "harvest", "holy"))},
            {assertThat(trie.getAllWordsByPrefix("ho"), hasItems("howl", "hollow", "hood", "holy"))},
            {assertThat(trie.getAllWordsByPrefix("hol"), hasItems("hollow", "holy"))},
            {assertThat(trie.getAllWordsByPrefix("ha"), hasItems("harvest"))}
        )

    }


}