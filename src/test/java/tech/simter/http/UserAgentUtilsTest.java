package tech.simter.http;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
class UserAgentUtilsTest {
  @Test
  void defaultUA() {
    assertEquals(UserAgentUtils.get("default"), UserAgentUtils.get());
    assertEquals(UserAgentUtils.get("not-exists"), UserAgentUtils.get());
    assertEquals(UserAgentUtils.get("xp-ie6"), UserAgentUtils.get("xp-ie"));
  }

  @Test
  void win10UA() {
    assertEquals(UserAgentUtils.get("win10-ie11"), UserAgentUtils.get("win10-ie"));
    assertThat(UserAgentUtils.get("win10-ie11")).contains("Windows NT 10.0", "rv:11.0");

    assertEquals(UserAgentUtils.get("win10-chrome46"), UserAgentUtils.get("win10-chrome"));
    assertThat(UserAgentUtils.get("win10-chrome46")).contains("Windows NT 10.0", "Chrome/46");

    assertEquals(UserAgentUtils.get("win10-firefox52"), UserAgentUtils.get("win10-firefox"));
    assertThat(UserAgentUtils.get("win10-firefox52")).contains("Windows NT 10.0", "Firefox/52");
  }

  @Test
  void win7UA() {
    assertEquals(UserAgentUtils.get("win7-ie9"), UserAgentUtils.get("win7-ie"));
    assertThat(UserAgentUtils.get("win7-ie9")).contains("Windows NT 6.1", "MSIE 9.0");

    assertEquals(UserAgentUtils.get("win7-chrome46"), UserAgentUtils.get("win7-chrome"));
    assertThat(UserAgentUtils.get("win7-chrome46")).contains("Windows NT 6.1", "Chrome/46");

    assertEquals(UserAgentUtils.get("win7-firefox45"), UserAgentUtils.get("win7-firefox"));
    assertThat(UserAgentUtils.get("win7-firefox45")).contains("Windows NT 6.1", "Firefox/45");
  }

  @Test
  void xpUA() {
    assertEquals(UserAgentUtils.get("xp-chrome46"), UserAgentUtils.get("xp-chrome"));
    assertThat(UserAgentUtils.get("xp-chrome46")).contains("Windows NT 5.1", "Chrome/46");

    assertEquals(UserAgentUtils.get("xp-ie6"), UserAgentUtils.get("xp-ie"));
    assertThat(UserAgentUtils.get("xp-ie6")).contains("Windows NT 5.1", "MSIE 6.0");

    assertEquals(UserAgentUtils.get("xp-firefox52"), UserAgentUtils.get("xp-firefox"));
    assertThat(UserAgentUtils.get("xp-firefox52")).contains("Windows NT 5.1", "Firefox/52");
  }

  @Test
  void androidUA() {
    assertEquals(UserAgentUtils.get("android-chrome44"), UserAgentUtils.get("android-chrome"));
    assertThat(UserAgentUtils.get("android-chrome44")).contains("Android", "Chrome/44");
  }

  @Test
  void iphoneUA() {
    assertEquals(UserAgentUtils.get("iphone-safari10"), UserAgentUtils.get("iphone-safari"));
    assertThat(UserAgentUtils.get("iphone-safari10")).contains("iPhone OS", "Version/10");
  }
}