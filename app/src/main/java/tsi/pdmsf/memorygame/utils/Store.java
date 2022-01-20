package tsi.pdmsf.memorygame.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tsi.pdmsf.memorygame.model.Score;
import tsi.pdmsf.memorygame.repository.ArrayRepository;
import tsi.pdmsf.memorygame.repository.IRepository;

public class Store {
    private static List<Score> mockList = new ArrayList<>(Arrays.asList(
            new Score("joao", 0, 50, 30, 5),
            new Score("gui", 0, 30, 30, 3)
    ));

    public static IRepository <Score> repository = new ArrayRepository<>(mockList);
}
