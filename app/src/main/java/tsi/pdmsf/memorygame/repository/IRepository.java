package tsi.pdmsf.memorygame.repository;

import java.util.List;

public interface IRepository <T>{
    public List<T> fetch(int level);

    public void save(T t);
}
