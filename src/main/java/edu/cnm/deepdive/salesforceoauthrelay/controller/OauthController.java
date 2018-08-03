package edu.cnm.deepdive.salesforceoauthrelay.controller;

import edu.cnm.deepdive.salesforceoauthrelay.model.BaseCredential;
import edu.cnm.deepdive.salesforceoauthrelay.model.OauthCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OauthController {

  @Value("${salesforce.authenticationUrl}")
  private String authenticationUrl;

  @Value("${salesforce.clientId}")
  private String clientId;

  @Value("${salesforce.clientSecret}")
  private String clientSecret;

  private HttpMessageConverters converters;

  @Autowired
  public OauthController(HttpMessageConverters converters) {
    this.converters = converters;
  }

  @PostMapping(value = "/authenticate",
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public OauthCredential authenticate(@RequestBody BaseCredential base) {
    if (base.getClientId() == null) {
      base.setClientId(clientId);
    }
    if (base.getClientSecret() == null) {
      base.setClientSecret(clientSecret);
    }
    RestTemplate template = new RestTemplate(converters.getConverters());
    return template.postForObject(authenticationUrl, base, OauthCredential.class);
  }

}
