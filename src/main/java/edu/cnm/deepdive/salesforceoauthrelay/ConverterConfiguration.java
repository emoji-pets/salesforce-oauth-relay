package edu.cnm.deepdive.salesforceoauthrelay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.cnm.deepdive.salesforceoauthrelay.model.BaseCredential;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@ComponentScan
public class ConverterConfiguration implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FormConverter converter = new FormConverter();
    converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
    converters.add(converter);
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
    converters.add(jsonConverter);
  }

  public static class FormConverter extends AbstractHttpMessageConverter<BaseCredential> {

    private static final FormHttpMessageConverter converter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> aClass) {
      return (BaseCredential.class == aClass);
    }

    @Override
    protected BaseCredential readInternal(Class<? extends BaseCredential> aClass,
        HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
      Map<String, String> map = converter.read(null, httpInputMessage).toSingleValueMap();
      return mapper.convertValue(map, BaseCredential.class);
    }

    @Override
    protected void writeInternal(BaseCredential baseCredential, HttpOutputMessage httpOutputMessage)
        throws IOException, HttpMessageNotWritableException {
        Map<String, Object> map = mapper.convertValue(baseCredential, new TypeReference<Map<String, Object>>() {});
        MultiValueMap<String, Object> mvmap = new LinkedMultiValueMap<>();
        mvmap.setAll(map);
        converter.write(mvmap, MediaType.APPLICATION_FORM_URLENCODED, httpOutputMessage);
    }
  }

}
