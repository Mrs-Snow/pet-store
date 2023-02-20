package com.mrsnow.petstore.controller;


import com.mrsnow.petstore.dao.Store;
import com.mrsnow.petstore.service.StoreService;
import com.mrsnow.petstore.utils.JO;
import com.mrsnow.petstore.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 商店表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/store")
@CrossOrigin
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @PostMapping(value = "/storeInfo")
    public R<Store> storeInfo(@RequestBody JO<Long> jo){
        Long id = jo.getData();
        Store store = storeService.getByUserId(id);
        if(store!=null){
            return R.success(store);
        }
        return R.success();
    }

}

