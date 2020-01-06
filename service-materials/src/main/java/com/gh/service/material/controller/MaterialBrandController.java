package com.gh.service.material.controller;

import com.gh.db.PageUtils;
import com.gh.pojo.entity.material.MaterialBrand;
import com.gh.pojo.vo.material.MaterialBrandVo;
import com.gh.service.common.utils.PageQuery;
import com.gh.service.material.service.MaterialBrandService;
import com.gh.util.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 品牌管理
 */
@Controller
@RequestMapping("/materialBrand")
public class MaterialBrandController {
    @Autowired
    private MaterialBrandService materialBrandService;


    /**
     * ①数据状态：全部、已上线、已下线、未上线。（默认为全部）0，已上线；1，已下线；2，未上线。 private Integer brandState;
     * ②数据类型：全部、正式、测试、运营（默认为正式）private Integer recordType;
     * ③模糊查询：品牌名称
     * 列表查询： /materialBrand/queryBrandPageData
     *  前端必须给定数据(当前页码和每页显示数量){"page":3,"limit":10}
     */
    @ResponseBody
    @PostMapping("/queryBrandPageData")
    public R<PageUtils<MaterialBrand>> queryBrandPageData(@RequestBody MaterialBrandVo materialBrandVo){
        PageQuery<MaterialBrandVo> query =new PageQuery<MaterialBrandVo>();
        query.setParamModel(materialBrandVo);
        query.setPage(materialBrandVo.getCurrentPage());
        //分页查询数据,返回分页信息
        PageUtils<MaterialBrand> pageUtils= materialBrandService.queryBrandList(query);

        return  R.ok(pageUtils);
    }

    /**
     * 保存
     * 新增： /materialBrand/saveMaterialBrand
     * 品牌名称   唯一约束
     * 前端:数据类型     品牌名称    排序
     * 默认是:未上线
     */
    @ResponseBody
    @RequestMapping("/saveMaterialBrand")
    public R<String> saveMaterialBrand(@RequestBody MaterialBrand materialBrand){
        try {
            if(materialBrand.getSort()!=null){
                int num=materialBrand.getSort().intValue();
                if(num<=0||num>9999){
                    return R.error("添加失败!排序值1~9999之间");
                }
            }
            materialBrandService.saveMaterialBrand(materialBrand);
            return R.ok();
        }catch (Exception e){
            //发生异常的时候,表明添加的品牌名称重复
            e.printStackTrace();
            return R.error("添加失败!品牌名称重复");
        }
    }

    /**
     * 修改/上下架/排序：/materialBrand/updateMaterialBrand
     */
    @ResponseBody
    @RequestMapping("/updateMaterialBrand")
    public R<String> updateMaterialBrand(@RequestBody MaterialBrandVo materialBrandVo){
        materialBrandService.updateMaterialBrand(materialBrandVo);
        return  R.ok("成功");
    }

}
