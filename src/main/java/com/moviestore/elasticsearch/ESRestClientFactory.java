package com.moviestore.elasticsearch;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClient.FailureListener;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.moviestore.elasticsearch.util.ConfigUtil;

public class ESRestClientFactory extends AbstractFactoryBean<RestClient> {

  public static final String DEAULT_CONFIG = "elasticsearch.properties";
  private RestClientBuilder restClientBuilder = null;
  private String configFileName = DEAULT_CONFIG;

  private FailureListener failureListener;
  private HttpClientConfigCallback httpClientConfigCallback;
  private int maxRetryTimeoutMillis = -1;

  private RestClient restClient;

  public ESRestClientFactory() {}

  public ESRestClientFactory(String configFile) {
    this.configFileName = configFile;
  }

  public void build() throws IOException {
    URL url = ConfigUtil.findFile(configFileName);

    Properties properties = new Properties();
    properties.load(new FileReader(new File(url.getFile())));

    HttpHost[] httpHosts = parseAndBuildHttpHosts(properties);

    restClientBuilder = RestClient.builder(httpHosts);

    if (failureListener != null) {
      restClientBuilder.setFailureListener(failureListener);
    }
    if (httpClientConfigCallback != null) {
      restClientBuilder.setHttpClientConfigCallback(httpClientConfigCallback);
    }

    if (maxRetryTimeoutMillis != -1) {
      restClientBuilder.setMaxRetryTimeoutMillis(maxRetryTimeoutMillis);
    }

    restClient = restClientBuilder.build();
  }

  private HttpHost[] parseAndBuildHttpHosts(Properties props) {
    List<HttpHost> httpHosts = new ArrayList<HttpHost>();
    for (Map.Entry<Object, Object> prop : props.entrySet()) {
      if (prop.getKey().toString().indexOf("elasticsearch.hosts") != -1) {
        String hostsStr = prop.getValue().toString();
        String[] esHosts = hostsStr.split(",");
        for (String esHost : esHosts) {
          String[] ehostProps = esHost.split(":");
          HttpHost host = null;
          if (ehostProps.length == 1) {
            host = new HttpHost(ehostProps[0]);
          } else if (ehostProps.length == 2) {
            host = new HttpHost(ehostProps[0], Integer.parseInt(ehostProps[1]));
          }
          httpHosts.add(host);
        }
      }
    }

    return httpHosts.toArray(new HttpHost[0]);
  }

  @Override
  public Class<?> getObjectType() {
    return RestClient.class;
  }

  @Override
  protected RestClient createInstance() throws Exception {
    if (restClient == null) {
      build();
    }
    return restClient;
  }
}
