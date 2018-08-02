package edu.cnm.deepdive.salesforceoauthrelay.controller;

import edu.cnm.deepdive.salesforceoauthrelay.ConverterConfiguration.FormConverter;
import edu.cnm.deepdive.salesforceoauthrelay.model.BaseCredential;
import edu.cnm.deepdive.salesforceoauthrelay.model.OauthCredential;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping("/authenticate")
public class OauthController {

  @Value("${salesforce.authenticationUrl}")
  private String authenticationUrl;

  @Value("${salesforce.clientId}")
  private String clientId;

  @Value("${salesforce.clientSecret}")
  private String clientSecret;

  @PostMapping(
      consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public OauthCredential authenticate(@RequestBody BaseCredential base) {
    if (base.getClientId() == null) {
      base.setClientId(clientId);
    }
    if (base.getClientSecret() == null) {
      base.setClientSecret(clientSecret);
    }
    RestTemplate template = new RestTemplate(
        Arrays.asList(new FormConverter(), new MappingJackson2HttpMessageConverter()));
    OauthCredential credential = template
        .postForObject(authenticationUrl, base, OauthCredential.class);
    return credential;
  }

}




























