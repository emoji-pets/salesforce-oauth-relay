package edu.cnm.deepdive.salesforceoauthrelay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OauthCredential {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("instance_url")
  private URL instanceUrl;

  private URL id;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("issued_at")
  private long issuedAt;

  private String signature;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public URL getInstanceUrl() {
    return instanceUrl;
  }

  public void setInstanceUrl(URL instanceUrl) {
    this.instanceUrl = instanceUrl;
  }

  public URL getId() {
    return id;
  }

  public void setId(URL id) {
    this.id = id;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public long getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(long issuedAt) {
    this.issuedAt = issuedAt;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

}
