package com.project.logic.codec;

public class CodecElement {

    private char element;
    private int quantity = 1;

    CodecElement(char element) {
        this.element = element;
    }

    public void inc() {
        this.quantity++;
    }

    @Override
    public String toString() {
        return String.valueOf(element).concat(quantity != 1 ? quantity + "|" : "");
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.element == ((CodecElement) obj).element;
    }

    @Override
    public int hashCode() {
        int prime = 11;
        return prime * this.element * this.quantity;
    }
}
