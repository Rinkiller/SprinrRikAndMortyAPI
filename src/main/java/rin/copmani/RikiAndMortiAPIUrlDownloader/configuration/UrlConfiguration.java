package rin.copmani.RikiAndMortiAPIUrlDownloader.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "urlconfiguration")
public class UrlConfiguration {
    String url;
}
