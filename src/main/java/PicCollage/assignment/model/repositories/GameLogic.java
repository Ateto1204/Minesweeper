package PicCollage.assignment.model.repositories;

import java.util.List;

import PicCollage.assignment.model.properties.Coordinate;

public interface GameLogic {
    void update(List<Coordinate> list);
    void cleanup();
    boolean validCheck(Coordinate pos, int height, int width);
}
