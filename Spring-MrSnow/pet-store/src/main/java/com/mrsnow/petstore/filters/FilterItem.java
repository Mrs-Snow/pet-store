package com.mrsnow.petstore.filters;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2023-02-09  15:02
 **/
@Component(value = "FilterItem")
@Data
@ConfigurationProperties(prefix = "my-filter")
public class FilterItem {
    private List<String> passList;
}
