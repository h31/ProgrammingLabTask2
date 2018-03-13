package com.project.insides;

public class ByteObject {

    private byte element;
    private int quantity = 1;

    public ByteObject(byte element) {
        this.element = element;
    }

    public void inc() {
        this.quantity++;
    }

    public byte getElement() {
        return element;
    }

    public boolean sameByte(byte other) {
        return this.element == other;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return element + (quantity != 1 ? "|" + quantity : "");
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.element == ((ByteObject) obj).element;
    }

    @Override
    public int hashCode() {
        int prime = 11;
        return prime * this.element * this.quantity;
    }
}
