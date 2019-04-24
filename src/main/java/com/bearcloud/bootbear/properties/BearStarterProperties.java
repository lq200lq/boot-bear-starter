package com.bearcloud.bootbear.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bear")
@Data
public class BearStarterProperties {

    private String version;

}
