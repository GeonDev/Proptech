package com.apt.api.config;


import com.apt.api.util.YamlPropertySourceFactory;
import lombok.Getter;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "table")
@PropertySource(value = "classpath:tableConfig.yml", factory = YamlPropertySourceFactory.class)
@Getter
@Setter
public class TableColumnConfig {

    private List<String> userColumn;

    private List<String> userSearch;

    private List<String> associateColumn;

    private List<String> associateSearch;

}
