package com.bahamazau.api.entity;

import java.util.Arrays;
import java.util.Optional;

public class ArrayEntity {

    private int[] data;

    public ArrayEntity(int[] data) {
        this.data = data;
    }

    public Optional<int[]> copyData() {
        if (this.data.length == 0) {
            return Optional.empty();
        }

        int[] data = new int[this.data.length];
        for (int index = 0; index < this.data.length; index++) {
            data[index] = this.data[index];
        }

        return Optional.ofNullable(data);
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayEntity that = (ArrayEntity) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "ArrayEntity{" +
                "currentArray=" + Arrays.toString(data) +
                '}';
    }
}
