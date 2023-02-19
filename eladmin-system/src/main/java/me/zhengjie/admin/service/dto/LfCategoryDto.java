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
package me.zhengjie.admin.service.dto;

import lombok.Data;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author chenjiajin
* @date 2023-02-17
**/
@Data
public class LfCategoryDto implements Serializable {

    /** 分类id */
    private Integer cateId;

    /** 分类名称 */
    private String cateName;

    /** 是否推荐(1推荐) */
    private Integer isTop;

    /** 图片 */
    private String coverImage;
}