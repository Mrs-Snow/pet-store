package com.mrsnow.petstore.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author MrSnow *** dz
 * @CreateTime: 2023-02-13  14:03
 **/
@Data
public class PJO<T> extends JO<T> implements Serializable {
    private static final long serialVersionUID=189751926745902L;

    private int current = 1;
    private int pageSize = 6;
}
