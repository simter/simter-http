package tech.simter.http;

import okhttp3.OkHttpClient;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author RJ 2017-03-29
 */
public class OkHttpUtilsTest {
  @Test
  public void timeout() {
    OkHttpClient client = OkHttpUtils.newClient();
    assertThat(client.connectTimeoutMillis(), is(20000));
    assertThat(client.readTimeoutMillis(), is(60000));
    assertThat(client.writeTimeoutMillis(), is(20000));
  }
}