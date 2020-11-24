package cn.yixianen.core.pojo.entity;

import cn.yixianen.core.pojo.specification.Specification;
import cn.yixianen.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * @author yixianen
 */
public class SpecEntity implements Serializable {
    /**
     * 规格
     */
    private Specification specification;
    /**
     * 规格属性列表
     */
    private List<SpecificationOption> specificationOptionList;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
