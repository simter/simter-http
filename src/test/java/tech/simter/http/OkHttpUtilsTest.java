package tech.simter.http;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RJ
 */
class OkHttpUtilsTest {
  @Test
  void timeout() {
    OkHttpClient client = OkHttpUtils.newClient();
    assertEquals(20000, client.connectTimeoutMillis());
    assertEquals(60000, client.readTimeoutMillis());
    assertEquals(20000, client.writeTimeoutMillis());
  }
}