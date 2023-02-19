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
package me.zhengjie.admin.service.impl;

import me.zhengjie.admin.domain.LfCategory;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.admin.repository.LfCategoryRepository;
import me.zhengjie.admin.service.LfCategoryService;
import me.zhengjie.admin.service.dto.LfCategoryDto;
import me.zhengjie.admin.service.dto.LfCategoryQueryCriteria;
import me.zhengjie.admin.service.mapstruct.LfCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author chenjiajin
* @date 2023-02-17
**/
@Service
@RequiredArgsConstructor
public class LfCategoryServiceImpl implements LfCategoryService {

    private final LfCategoryRepository lfCategoryRepository;
    private final LfCategoryMapper lfCategoryMapper;

    @Override
    public Map<String,Object> queryAll(LfCategoryQueryCriteria criteria, Pageable pageable){
        Page<LfCategory> page = lfCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(lfCategoryMapper::toDto));
    }

    @Override
    public List<LfCategoryDto> queryAll(LfCategoryQueryCriteria criteria){
        return lfCategoryMapper.toDto(lfCategoryRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public LfCategoryDto findById(Integer cateId) {
        LfCategory lfCategory = lfCategoryRepository.findById(cateId).orElseGet(LfCategory::new);
        ValidationUtil.isNull(lfCategory.getCateId(),"LfCategory","cateId",cateId);
        return lfCategoryMapper.toDto(lfCategory);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LfCategoryDto create(LfCategory resources) {
        return lfCategoryMapper.toDto(lfCategoryRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(LfCategory resources) {
        LfCategory lfCategory = lfCategoryRepository.findById(resources.getCateId()).orElseGet(LfCategory::new);
        ValidationUtil.isNull( lfCategory.getCateId(),"LfCategory","id",resources.getCateId());
        lfCategory.copy(resources);
        lfCategoryRepository.save(lfCategory);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer cateId : ids) {
            lfCategoryRepository.deleteById(cateId);
        }
    }

    @Override
    public void download(List<LfCategoryDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LfCategoryDto lfCategory : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("分类名称", lfCategory.getCateName());
            map.put("是否推荐(1推荐)", lfCategory.getIsTop());
            map.put("图片", lfCategory.getCoverImage());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}