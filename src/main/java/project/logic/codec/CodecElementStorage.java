package main.java.project.logic.codec;

public class CodecElementStorage {

    private char element;
    private int quantity = 1;

    CodecElementStorage(char element) {
        this.element = element;
    }

    public void inc() {
        this.quantity++;
    }

    @Override
    public String toString() {
        return element + (quantity != 1 ? quantity + "|" : "");
    }

    @Override
    public boolean equals(Object obj) {
        return this.getClass() == obj.getClass() && this.element == ((CodecElementStorage) obj).element;
    }

    @Override
    public int hashCode() {
        int prime = 11;
        return prime * this.element * this.quantity;
    }
}
