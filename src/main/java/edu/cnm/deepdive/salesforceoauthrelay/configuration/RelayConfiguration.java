package edu.cnm.deepdive.salesforceoauthrelay.configuration;

import edu.cnm.deepdive.salesforceoauthrelay.converter.BaseCredentialFormConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelayConfiguration {

  @Bean
  public HttpMessageConverters converters() {
    return new HttpMessageConverters(new BaseCredentialFormConverter());
  }

}
