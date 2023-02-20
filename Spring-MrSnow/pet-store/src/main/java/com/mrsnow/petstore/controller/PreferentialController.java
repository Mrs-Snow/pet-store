package com.mrsnow.petstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mrsnow.petstore.dao.Preferential;
import com.mrsnow.petstore.service.PreferentialService;
import com.mrsnow.petstore.utils.PJO;
import com.mrsnow.petstore.utils.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 优惠表 前端控制器
 * </p>
 *
 * @author mrsnow
 * @since 2022-12-14
 */
@RestController
@RequestMapping("/preferential")
@CrossOrigin
@RequiredArgsConstructor
public class PreferentialController {
    private final PreferentialService preferentialService;

    @PostMapping(value = "/list")
    public R<IPage<Preferential>> list(@RequestBody PJO<Long> pjo){
        pjo.setPageSize(4);
        IPage<Preferential> pageVo = preferentialService.pageQuery(pjo);
        return R.success(pageVo);
    }
}

