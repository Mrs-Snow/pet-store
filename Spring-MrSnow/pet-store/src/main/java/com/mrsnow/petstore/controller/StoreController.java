package com.mrsnow.petstore.controller;


import com.mrsnow.petstore.dao.Store;
import com.mrsnow.petstore.dao.User;
import com.mrsnow.petstore.service.StoreService;
import com.mrsnow.petstore.service.UserService;
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
    private final UserService userService;
    @PostMapping(value = "/storeInfo")
    public R<Store> storeInfo(@RequestBody JO<String> jo){
        Long id = Long.parseLong(jo.getData());
        Store store = storeService.getByUserId(id);
        if(store!=null){
            return R.success(store);
        }
        Store newStore = new Store();
        User user = userService.getById(id);
        newStore.setManagerName(user.getNickName());
        return R.success(newStore);
    }

    @PostMapping(value = "/edit")
    public R<Store> editStore(@RequestBody JO<Store> jo){
        String message = storeService.editStore(jo);
        return R.success(jo.getData(),message);
    }

}

