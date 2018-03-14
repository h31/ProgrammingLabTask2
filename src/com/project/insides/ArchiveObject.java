package com.project.insides;

public class ArchiveObject {

    private char element;
    private int quantity = 1;

    public ArchiveObject(char element) {
        this.element = element;
    }

    public void inc() {
        this.quantity++;
    }

    public char getElement() {
        return element;
    }

    public boolean sameElement(char other) {
        return this.element == other;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(element).concat(quantity != 1 ? "|" + quantity : "");
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.element == ((ArchiveObject) obj).element;
    }

    @Override
    public int hashCode() {
        int prime = 11;
        return prime * this.element * this.quantity;
    }
}
