package cn.gmsj.evaluationsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域处理配置
 *
 * @author Alan
 */
@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        /**
         * 1允许任何域名使用
         */
        corsConfiguration.addAllowedOrigin("*");
        /**
         * 2允许任何头
         */
        corsConfiguration.addAllowedHeader("*");
        /**
         * 3允许任何方法（post、get等）
         */
        corsConfiguration.addAllowedMethod("*");
        /**
         * 4允许feach从响应头中可以获取的header
         */
        corsConfiguration.addExposedHeader(
                "Authorization,GM-Authorization,Content-Disposition,"
                        + "GM-AdministratorAuthorization");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
