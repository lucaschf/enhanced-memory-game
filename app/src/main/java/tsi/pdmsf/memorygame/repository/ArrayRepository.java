package tsi.pdmsf.memorygame.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tsi.pdmsf.memorygame.model.Score;

public class ArrayRepository <T> implements  IRepository <T>{
    private List<T> dbList;

    public ArrayRepository() {
        this.dbList = new ArrayList<>();
    }

    public ArrayRepository(List<T> dbList) {
        this.dbList = dbList;
    }

    @Override
    public List<T> fetch(int level) {
        List<T> filteredList = dbList.stream()
                .collect(Collectors.toList());
        return filteredList;
    }

    @Override
    public void save(T t) {
        dbList.add(t);
    }
}
