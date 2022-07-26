package com.discovery.sonic.recruitment;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.WillNotClose;

public class DataSources {

  private static final Pattern commaPattern = Pattern.compile("\\s*,\\s*");

  private static final Path dataRoot = Paths.get("src", "main", "resources");
  private static final Path usersFile = dataRoot.resolve("users.csv");
  private static final Path userPreferencesFile = dataRoot.resolve("user_preferences.csv");
  private static final Path videosFile = dataRoot.resolve("videos.csv");
  private static final Path categoriesFile = dataRoot.resolve("categories.csv");
  private static final Path videoCategoriesFile = dataRoot.resolve("video_categories.csv");
  private static final Path videoViewsFile = dataRoot.resolve("video_views.csv");

  @WillNotClose
  public static Stream<String[]> users() {
    return readCsvFile(usersFile);
  }

  public static List<String[]> usersList() {
    try (var stream = users()) {
      return stream.collect(toList());
    }
  }

  @WillNotClose
  public static Stream<String[]> userPreferences() {
    return readCsvFile(userPreferencesFile);
  }

  public static List<String[]> userPreferencesList() {
    try (var stream = userPreferences()) {
      return stream.collect(toList());
    }
  }

  @WillNotClose
  public static Stream<String[]> videos() {
    return readCsvFile(videosFile);
  }

  public static List<String[]> videosList() {
    try (var stream = videos()) {
      return stream.collect(toList());
    }
  }

  @WillNotClose
  public static Stream<String[]> categories() {
    return readCsvFile(categoriesFile);
  }

  public static List<String[]> categoriesList() {
    try (var stream = categories()) {
      return stream.collect(toList());
    }
  }

  @WillNotClose
  public static Stream<String[]> videoCategories() {
    return readCsvFile(videoCategoriesFile);
  }

  public static List<String[]> videoCategoriesList() {
    try (var stream = videoCategories()) {
      return stream.collect(toList());
    }
  }

  @WillNotClose
  public static Stream<String[]> videoViews() {
    return readCsvFile(videoViewsFile);
  }

  public static List<String[]> videoViewsList() {
    try (var stream = videoViews()) {
      return stream.collect(toList());
    }
  }

  private static Stream<String[]> readCsvFile(Path filePath) {
    try {
      return Files.lines(filePath)
          .skip(1)
          .filter(not(String::isBlank))
          .map(commaPattern::split);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }
}
