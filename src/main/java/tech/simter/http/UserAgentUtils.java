package tech.simter.http;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Browser UserAgent collection and tools
 * <p>see http://rongjih.blog.163.com/blog/static/335744612011821111612884</p>
 *
 * @author RJ 2017-03-29
 */
public class UserAgentUtils {
  private static Map<String, String> agents = new ConcurrentHashMap<>();

  static {
    // initial default user-agents

    // win10
    agents.put("win10-ie11", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
    agents.put("win10-ie", agents.get("win10-ie11"));

    agents.put("win10-edge", "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
      "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393");

    agents.put("win10-chrome46",
      "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
    agents.put("win10-chrome56",
      "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
    agents.put("win10-chrome", agents.get("win10-chrome46"));

    agents.put("win10-firefox52", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
    agents.put("win10-firefox", agents.get("win10-firefox52"));

    // win7
    agents.put("win7-ie8", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)");
    agents.put("win7-ie9", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");
    agents.put("win7-ie10", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
    agents.put("win7-ie", agents.get("win7-ie9"));

    agents.put("win7-chrome46",
      "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
    agents.put("win7-chrome", agents.get("win7-chrome46"));

    agents.put("win7-firefox45", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
    agents.put("win7-firefox", agents.get("win7-firefox45"));


    // winXP
    agents.put("xp-ie8", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)");
    agents.put("xp-ie7", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
    agents.put("xp-ie6", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
    agents.put("xp-ie", agents.get("xp-ie6"));

    agents.put("xp-chrome46",
      "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
    agents.put("xp-chrome", agents.get("xp-chrome46"));

    agents.put("xp-firefox52", "Mozilla/5.0 (Windows NT 5.1; rv:52.0) Gecko/20100101 Firefox/52.0");
    agents.put("xp-firefox", agents.get("xp-firefox52"));

    // iphone
    agents.put("iphone-safari10", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 " +
      "(KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1");
    agents.put("iphone-safari", agents.get("iphone-safari10"));

    // android
    agents.put("android-chrome44", "Mozilla/5.0 (Linux; Android 6.0.1; SAMSUNG SM-N9200 Build/MMB29K) " +
      "AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/4.2 Chrome/44.0.2403.133 Mobile Safari/537.36");
    agents.put("android-chrome", agents.get("android-chrome44"));

    // default
    agents.put("default", agents.get("win7-ie"));
  }

  /**
   * Get a UserAgent by key
   * <p>If key not exists, return a default UserAgent</p>
   */
  public static String get(String key) {
    return agents.containsKey(key) ? agents.get(key) : agents.get("default");
  }

  /**
   * Get a Default UserAgent
   * <p>equals to get("default")</p>
   */
  public static String get() {
    return agents.get("default");
  }

  /**
   * All available keys
   */
  public static Set<String> keys() {
    return agents.keySet();
  }

  /**
   * Add a UserAgent
   */
  public static void add(String key, String userAgent) {
    agents.put(key, userAgent);
  }
}