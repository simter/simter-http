package tech.simter.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * @author RJ 2017-03-29
 */
public final class OkHttpUtils {
  private final static Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);
  public static final String SIMTER_PROXY_ENABLE = "simter_proxy_enable";
  public static final String SIMTER_PROXY_HOSTNAME = "simter_proxy_hostname";
  public static final String SIMTER_PROXY_PORT = "simter_proxy_port";
  private static Proxy proxy;

  /**
   * Set a global proxy
   *
   * @param hostname The Host name
   * @param port     The port number
   */
  public static void proxy(String hostname, int port) {
    proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
  }

  /**
   * get a proxy through system environment config
   * <ul>
   * <li>'simter.proxy.enable' = 'true' : return a new proxy through 'simter.proxy.hostname' and 'simter.proxy.port' config, default to 127.0.0.1:8888</li>
   * <li>'simter.proxy.enable' = 'false' or no 'simter.proxy.enable' config : no proxy use, return null</li>
   * </ul>
   *
   * @return the proxy
   */
  public static Proxy getEnvProxy() {
    // Whether to enable the proxy
    boolean enableProxy;
    try {
      enableProxy = Boolean.parseBoolean(System.getenv(SIMTER_PROXY_ENABLE));
    } catch (Exception e) {
      logger.warn(e.getMessage(), e);
      enableProxy = false;
    }
    if (!enableProxy) return null; // don't use proxy

    // get hostname, default to "127.0.0.1"
    String hostname = null;
    try {
      hostname = System.getenv(SIMTER_PROXY_HOSTNAME);
    } catch (Exception e) {
      logger.warn(e.getMessage(), e);
    }
    if (hostname == null) hostname = "127.0.0.1";

    // get port, default to 8888
    String port_ = null;
    try {
      port_ = System.getenv(SIMTER_PROXY_PORT);
    } catch (Exception e) {
      logger.warn(e.getMessage(), e);
    }
    int port;
    try {
      port = port_ == null ? 8888 : Integer.parseInt(port_);
    } catch (Exception e) {
      logger.warn(e.getMessage(), e);
      port = 8888;
    }

    logger.info("use proxy {}:{}", hostname, port);
    return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(hostname, port));
  }

  /**
   * Create a new OkHttpClient instance with simter default config:
   * <ul>
   * <li>1) proxy config through system environment</li>
   * <li>2) connectTimeout = 20s, readTimeout = 30s, writeTimeout = 20s</li>
   * </ul>
   *
   * @return the client instance
   */
  public static OkHttpClient newClient() {
    return newClientBuilder().build();
  }


  /**
   * Create a new OkHttpClient.Builder instance with simter default config:
   * <ul>
   * <li>1) proxy config through system environment</li>
   * <li>2) connectTimeout = 20s, readTimeout = 60s, writeTimeout = 20s</li>
   * </ul>
   *
   * @return the builder instance
   */
  public static OkHttpClient.Builder newClientBuilder() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    // proxy
    if (proxy != null) {    // use custom proxy
      builder.proxy(proxy);
    } else {                // use default proxy from system environment config
      Proxy envProxy = getEnvProxy();
      if (envProxy != null) builder.proxy(envProxy);
    }

    // timeout: OkHttp default is connectTimeout = 10s, readTimeout = 10s, writeTimeout = 10s, pingInterval = 0;
    builder.connectTimeout(20, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(20, TimeUnit.SECONDS);

    return builder;
  }

  /**
   * Create a new Request.Builder instance with simter default config:
   * <ul>
   * <li>Header: User-Agent = ${UserAgentUtils.get()}</li>
   * </ul>
   *
   * @return the builder instance
   */
  public static Request.Builder newRequestBuilder() {
    return new Request.Builder().addHeader("User-Agent", UserAgentUtils.get());
  }
}