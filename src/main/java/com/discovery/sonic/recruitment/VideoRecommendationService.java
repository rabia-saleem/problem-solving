package com.discovery.sonic.recruitment;

import static java.util.Collections.emptyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VideoRecommendationService {

  public List<Integer> getTop3RecommendedVideos(int userId) {
     String id = String.valueOf(userId);
    List<String>likedCategories = DataSources.userPreferences()
            .filter(up->(""+userId).equals(up[0]))
            .filter(up-> {
                return up[1].equals("LIKES");
            })
            .map(up->up[2]).collect(Collectors.toList());

    List<String>likedVideoCategories = DataSources.videoCategories()
            .filter(vc->likedCategories.contains(vc[1]))
            .map(vc->vc[0]).collect(Collectors.toList());

    List<String>viewedVideos = DataSources.videoViews()
            .filter(vv->likedVideoCategories.contains(vv[1]))
            .map(vv->vv[1])
            .collect(Collectors.toList());
    likedCategories.removeAll(viewedVideos);
Map<String,Integer> map = new HashMap<>();


   return likedCategories.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
  }

  public static void main(String[] args) {
    VideoRecommendationService vc = new VideoRecommendationService();

    System.out.println(  vc.getTop3RecommendedVideos(3));
  }
}
