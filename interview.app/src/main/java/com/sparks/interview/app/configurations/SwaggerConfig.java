//package com.sparks.interview.app.configurations;
//
//
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("public")
//                .pathsToMatch("/**")
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi regularUserApi() {
//        return GroupedOpenApi.builder()
//                .group("regular")
//                .pathsToMatch("/**")
//                .addOpenApiCustomizer(openApi -> {
//                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                    if (authentication != null) {
//                        String role = authentication.getAuthorities().stream()
//                                .map(auth -> auth.getAuthority())
//                                .findFirst().orElse("");
//
//                        if ("REGULAR".equals(role)) {
//                            openApi.getPaths().entrySet().removeIf(entry ->
//                                    entry.getValue().getGet() == null &&
//                                            entry.getValue().getPost() == null);
//                        }
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("admin")
//                .pathsToMatch("/**")
//                .addOpenApiCustomizer(openApi -> {
//                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                    if (authentication != null) {
//                        String role = authentication.getAuthorities().stream()
//                                .map(auth -> auth.getAuthority())
//                                .findFirst().orElse("");
//
//                        if ("ADMIN".equals(role)) {
//                            // Show all APIs for admin
//                        }
//                    }
//                })
//                .build();
//    }
//}