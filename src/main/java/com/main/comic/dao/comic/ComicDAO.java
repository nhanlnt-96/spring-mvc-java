package com.main.comic.dao.comic;

import com.main.comic.entity.Comic;

import java.util.List;

public interface ComicDAO {
    List<Comic> getAllComics();

    void saveComicData(Comic comicData);

    Comic getComicById(int comicId);

    void removeComicById(int comicId);

    boolean checkComicIsExist(String comicName, int comicId, int categoryId);
}
