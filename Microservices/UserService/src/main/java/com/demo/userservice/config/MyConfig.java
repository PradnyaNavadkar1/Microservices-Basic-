package com.demo.userservice.config;

import com.demo.userservice.config.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Configuration      //this class level annotation

public class MyConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){


        RestTemplate restTemplate=new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptor=new ArrayList<>();

        interceptor.add(new RestTemplateInterceptor(manager(
                clientRegistrationRepository, oAuth2AuthorizedClientRepository

        )));
        restTemplate.setInterceptors(interceptor);

        return new RestTemplate();
    }



     @Bean
     @LoadBalanced   //it is Interface  if there is multple instances the nfor load balnaceing purpose we have to use load balennce
     public WebClient webClient(){


        return WebClient.builder().build();
}


     //declare the bean of OAuth2AuthorizedClient maanger
    @Bean
    public OAuth2AuthorizedClientManager manager(
            ClientRegistrationRepository clientRegistrationRepository, // it provides the capability to store the copy of client information from authorization server
            OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository //it manages information about the client
    ){

        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build(); //


        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,auth2AuthorizedClientRepository);
         defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
        return defaultOAuth2AuthorizedClientManager;
    }


}
