package tech.simter.http;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author RJ 2017-03-29
 */
public class UserAgentUtilsTest {
  @Test
  public void defaultUA() throws InterruptedException {
    assertThat(UserAgentUtils.get(), is(UserAgentUtils.get("default")));
    assertThat(UserAgentUtils.get(), is(UserAgentUtils.get("not-exists")));
    assertThat(UserAgentUtils.get("xp-ie"), is(UserAgentUtils.get("xp-ie6")));
  }

  @Test
  public void win10UA() throws InterruptedException {
    assertThat(UserAgentUtils.get("win10-ie"), is(UserAgentUtils.get("win10-ie11")));
    assertThat(UserAgentUtils.get("win10-ie11"), allOf(
      containsString("Windows NT 10.0"),
      containsString("rv:11.0")
    ));

    assertThat(UserAgentUtils.get("win10-chrome"), is(UserAgentUtils.get("win10-chrome46")));
    assertThat(UserAgentUtils.get("win10-chrome46"), allOf(
      containsString("Windows NT 10.0"),
      containsString("Chrome/46")
    ));

    assertThat(UserAgentUtils.get("win10-firefox"), is(UserAgentUtils.get("win10-firefox52")));
    assertThat(UserAgentUtils.get("win10-firefox52"), allOf(
      containsString("Windows NT 10.0"),
      containsString("Firefox/52")
    ));
  }

  @Test
  public void win7UA() throws InterruptedException {
    assertThat(UserAgentUtils.get("win7-ie"), is(UserAgentUtils.get("win7-ie9")));
    assertThat(UserAgentUtils.get("win7-ie9"), allOf(
      containsString("Windows NT 6.1"),
      containsString("MSIE 9.0")
    ));

    assertThat(UserAgentUtils.get("win7-chrome"), is(UserAgentUtils.get("win7-chrome46")));
    assertThat(UserAgentUtils.get("win7-chrome46"), allOf(
      containsString("Windows NT 6.1"),
      containsString("Chrome/46")
    ));

    assertThat(UserAgentUtils.get("win7-firefox"), is(UserAgentUtils.get("win7-firefox45")));
    assertThat(UserAgentUtils.get("win7-firefox45"), allOf(
      containsString("Windows NT 6.1"),
      containsString("Firefox/45")
    ));
  }

  @Test
  public void xpUA() throws InterruptedException {
    assertThat(UserAgentUtils.get("xp-chrome"), is(UserAgentUtils.get("xp-chrome46")));
    assertThat(UserAgentUtils.get("xp-chrome46"), allOf(
      containsString("Windows NT 5.1"),
      containsString("Chrome/46")
    ));

    assertThat(UserAgentUtils.get("xp-ie"), is(UserAgentUtils.get("xp-ie6")));
    assertThat(UserAgentUtils.get("xp-ie6"), allOf(
      containsString("Windows NT 5.1"),
      containsString("MSIE 6.0")
    ));

    assertThat(UserAgentUtils.get("xp-firefox"), is(UserAgentUtils.get("xp-firefox52")));
    assertThat(UserAgentUtils.get("xp-firefox52"), allOf(
      containsString("Windows NT 5.1"),
      containsString("Firefox/52")
    ));
  }

  @Test
  public void androidUA() throws InterruptedException {
    assertThat(UserAgentUtils.get("android-chrome"), is(UserAgentUtils.get("android-chrome44")));
    assertThat(UserAgentUtils.get("android-chrome44"), allOf(
      containsString("Android"),
      containsString("Chrome/44")
    ));
  }

  @Test
  public void iphoneUA() throws InterruptedException {
    assertThat(UserAgentUtils.get("iphone-safari"), is(UserAgentUtils.get("iphone-safari10")));
    assertThat(UserAgentUtils.get("iphone-safari10"), allOf(
      containsString("iPhone OS"),
      containsString("Version/10")
    ));
  }
}