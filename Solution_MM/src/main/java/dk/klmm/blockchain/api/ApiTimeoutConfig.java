package dk.klmm.blockchain.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Micha
 */
@Configuration
public class ApiTimeoutConfig {
    
    public RestTemplate getApiRestTemplateTimeout(Integer timeOutMs){
        return new RestTemplate(clientHttpRequestFactory(timeOutMs));
    }

    private ClientHttpRequestFactory clientHttpRequestFactory(Integer timeOutMs) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(timeOutMs);
        factory.setConnectTimeout(timeOutMs);
        return factory;
    }
}
