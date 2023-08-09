package com.demo.gateway.ApiGateWay.models;


import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

   private String userId;
   private String accessToken;
   private String refreshToken;
   private long expireAt;
   private Collection<String> authorities;



}
