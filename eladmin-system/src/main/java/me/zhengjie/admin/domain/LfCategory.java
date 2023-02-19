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
package me.zhengjie.admin.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author chenjiajin
* @date 2023-02-17
**/
@Entity
@Data
@Table(name="lf_category")
public class LfCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`cate_id`")
    @ApiModelProperty(value = "分类id")
    private Integer cateId;

    @Column(name = "`cate_name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "分类名称")
    private String cateName;

    @Column(name = "`is_top`")
    @ApiModelProperty(value = "是否推荐(1推荐)")
    private Integer isTop;

    @Column(name = "`cover_image`")
    @ApiModelProperty(value = "图片")
    private String coverImage;

    public void copy(LfCategory source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
