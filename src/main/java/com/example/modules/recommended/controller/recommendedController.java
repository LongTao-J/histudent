package com.example.modules.recommended.controller;

import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author mushan
 * @date 20/9/2022
 * @apiNote
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/recommended")
public class recommendedController {
    @GetMapping("/get/all-res")
    @CrossOrigin
    public R<Object> getAllRes() {
        return R.error();
    }
}
