package com.main.comic.service.comic;

import com.main.comic.entity.Comic;

import java.util.List;

public interface ComicService {
    List<Comic> getAllComics();

    void saveComicData(Comic comicData);

    Comic getComicById(int comicId);

    void removeComicById(int comicId);

    boolean checkComicIsExist(String comicName, int comicId, int categoryId);
}
