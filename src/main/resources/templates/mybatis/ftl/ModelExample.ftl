package ${packageName}.model;

<#list dataTypeList as dataType>
<#if dataType == "JSONObject">
import com.alibaba.fastjson.JSONObject;
</#if>
</#list>

import org.mybatis.plugin.Criterion;

import java.util.ArrayList;
<#list dataTypeList as dataType>
<#if dataType == "BigDecimal">
import java.math.BigDecimal;
</#if>
<#if dataType == "BigInteger">
import java.math.BigInteger;
</#if>
<#if dataType == "Date">
import java.sql.Date;
</#if>
<#if dataType == "Time">
import java.sql.Time;
</#if>
<#if dataType == "Timestamp">
import java.sql.Timestamp;
</#if>
</#list>
import java.util.List;

public class ${className}Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ${className}Example() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }


        <#list propertyList as property>
        public Criteria and${property.propertyName?cap_first}IsNull() {
            addCriterion("${property.columnName} is null");
            return (Criteria) this;
        }
        public Criteria and${property.propertyName?cap_first}IsNotNull() {
            addCriterion("${property.columnName} is not null");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}EqualTo(${property.propertyType} value) {
            addCriterion("${property.columnName} =", value, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}NotEqualTo(${property.propertyType} value) {
            addCriterion("${property.columnName} <>", value, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}GreaterThan(${property.propertyType} value) {
            addCriterion("${property.columnName} >", value, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}GreaterThanOrEqualTo(${property.propertyType} value) {
            addCriterion("${property.columnName} >=", value, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}LessThan(${property.propertyType} value) {
            addCriterion("${property.columnName} <", value, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}LessThanOrEqualTo(${property.propertyType} value) {
            addCriterion("${property.columnName} <=", value, "${property.propertyName}");
            return (Criteria) this;
        }

        <#if property.propertyType == "String">
        public Criteria and${property.propertyName?cap_first}Like(${property.propertyType} value) {
            addCriterion("${property.columnName} like", value, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}NotLike(${property.propertyType} value) {
            addCriterion("${property.columnName} not like", value, "${property.propertyName}");
            return (Criteria) this;
        }
        </#if>

        public Criteria and${property.propertyName?cap_first}In(List<${property.propertyType}> values) {
            addCriterion("${property.columnName} in", values, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}NotIn(List<${property.propertyType}> values) {
            addCriterion("${property.columnName} not in", values, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}Between(${property.propertyType} value1, ${property.propertyType} value2) {
            addCriterion("${property.columnName} between", value1, value2, "${property.propertyName}");
            return (Criteria) this;
        }

        public Criteria and${property.propertyName?cap_first}NotBetween(${property.propertyType} value1, ${property.propertyType} value2) {
            addCriterion("${property.columnName} not between", value1, value2, "${property.propertyName}");
            return (Criteria) this;
        }
        </#list>

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

}