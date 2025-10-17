//package ci.trabrouss.gatewayservice.routes;
//
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.RouterFunction;
//
//import java.util.List;
//
//@Configuration
//public class GatewayMvcDiscoveryConfig {
//
//    private final DiscoveryClient discoveryClient;
//    public GatewayMvcDiscoveryConfig(DiscoveryClient discoveryClient) {
//        this.discoveryClient = discoveryClient;
//    }
//
//    @Bean
//    public RouterFunction<?> dynamicRoutes() {
//        GatewayRouterFunctions.Builder builder = GatewayRouterFunctions.route();
//
//        List<String> services = discoveryClient.getServices();
//        for (String serviceId : services) {
//            builder.route(serviceId, r -> r.path("/" + serviceId + "/**")
//                    .uri("lb://" + serviceId));
//        }
//
//        return builder.build();
//    }
//}
