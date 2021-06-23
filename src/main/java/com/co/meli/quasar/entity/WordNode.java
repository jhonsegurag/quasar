package com.co.meli.quasar.entity;

public class WordNode {
    private int index;
    private String word;

    public WordNode(int index, String word) {
        this.index = index;
        this.word = word;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
