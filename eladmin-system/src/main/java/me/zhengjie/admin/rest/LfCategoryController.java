/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.admin.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.admin.domain.LfCategory;
import me.zhengjie.admin.service.LfCategoryService;
import me.zhengjie.admin.service.dto.LfCategoryQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* @website https://eladmin.vip
* @author chenjiajin
* @date 2023-02-17
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "分类管理管理")
@RequestMapping("/api/lfCategory")
public class LfCategoryController {

    private final LfCategoryService lfCategoryService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('lfCategory:list')")
    public void exportLfCategory(HttpServletResponse response, LfCategoryQueryCriteria criteria) throws IOException {
        lfCategoryService.download(lfCategoryService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询分类管理")
    @ApiOperation("查询分类管理")
    @PreAuthorize("@el.check('lfCategory:list')")
    public ResponseEntity<Object> queryLfCategory(LfCategoryQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(lfCategoryService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增分类管理")
    @ApiOperation("新增分类管理")
    @PreAuthorize("@el.check('lfCategory:add')")
    public ResponseEntity<Object> createLfCategory(@Validated @RequestBody LfCategory resources){
        return new ResponseEntity<>(lfCategoryService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改分类管理")
    @ApiOperation("修改分类管理")
    @PreAuthorize("@el.check('lfCategory:edit')")
    public ResponseEntity<Object> updateLfCategory(@Validated @RequestBody LfCategory resources){
        lfCategoryService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除分类管理")
    @ApiOperation("删除分类管理")
    @PreAuthorize("@el.check('lfCategory:del')")
    public ResponseEntity<Object> deleteLfCategory(@RequestBody Integer[] ids) {
        lfCategoryService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}