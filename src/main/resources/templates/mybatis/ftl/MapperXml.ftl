<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${className}Mapper">
    <resultMap id="${className}" type="${packageName}.model.${className}">
    <#list propertyList as property>
        <#if property.columnType =='json'>
        <result column="${property.columnName}" property="${property.propertyName}"
                typeHandler="org.mybatis.plugin.JSONTypeHandler"/>
        <#elseif property.columnType =='jsonb'>
        <result column="${property.columnName}" property="${property.propertyName}"
                typeHandler="org.mybatis.plugin.JSONBinaryTypeHandler"/>
        <#elseif property.propertyType?contains('[]')>
        <result column="${property.columnName}" property="${property.propertyName}"
                typeHandler="org.mybatis.plugin.ArrayTypeHandler"/>
        <#else >
        <result column="${property.columnName}" property="${property.propertyName}"/>
        </#if>
    </#list>
    </resultMap>

    <sql id="Example_Where_Clause">
        <where>
            <foreach collection="example.oredCriteria" item="criteria" separator="or">
                <if test="criteria.valid">
                    <trim prefix="(" prefixOverrides="and" suffix=")">
                        <foreach collection="criteria.criteria" item="criterion">
                            <choose>
                                <when test="criterion.noValue">
                                    and ${r'${'}criterion.condition${r'}'}
                                </when>
                                <when test="criterion.singleValue">
                                    and ${r'${'}criterion.condition${r'}'} ${r'#{'}criterion.value${r'}'}
                                </when>
                                <when test="criterion.betweenValue">
                                    and ${r'${'}criterion.condition${r'}'} ${r'#{'}criterion.value${r'}'} and ${r'#{'}criterion.secondValue${r'}'}
                                </when>
                                <when test="criterion.listValue">
                                    and ${r'${'}criterion.condition${r'}'}
                                    <foreach close=")" collection="criterion.value" item="listItem" open="("
                                             separator=",">
                                    ${r'${'}listItem${r'}'}
                                    </foreach>
                                </when>
                            </choose>
                        </foreach>
                    </trim>
                </if>
            </foreach>
        </where>
    </sql>

    <sql id="Base_Column_List"> <#list propertyList as property>${property.columnName}<#sep>,</#list></sql>

    <select id="countByExample" resultType="java.lang.Long">
        select count(*) from ${tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </select>

    <delete id="deleteByExample">
        delete from ${tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </delete>

    <insert id="insertSelective" parameterType="${packageName}.model.${className}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
        <#list propertyList as property>
            <if test="${property.propertyName} != null">${property.columnName},</if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
        <#list propertyList as property>
            <if test="${property.propertyName} != null">
                <#if property.columnType =='json'>
                ${r'#{'}${property.propertyName}${r', typeHandler=org.mybatis.plugin.JSONTypeHandler}'},
                <#elseif property.columnType =='jsonb'>
                ${r'#{'}${property.propertyName}${r', typeHandler=org.mybatis.plugin.JSONBinaryTypeHandler}'},
                <#elseif property.propertyType?contains('[]')>
                ${r'#{'}${property.propertyName}${r', typeHandler=org.mybatis.plugin.ArrayTypeHandler}'},
                <#else >
                ${r'#{'}${property.propertyName}${r'}'},
                </#if>
            </if>
        </#list>
        </trim>
    </insert>

    <select id="selectByExample" resultMap="${className}">
        select
        <if test="example.distinct">
            distinct
        </if>
        <include refid="Base_Column_List"/>
        from ${tableName}
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
        <if test="example.orderByClause != null">
            order by ${r'${'}orderByClause${r'}'}
        </if>
    </select>

    <update id="updateByExampleSelective">
        update ${tableName}
        <set>
        <#list propertyList as property>
            <if test="record.${property.propertyName} != null">
                <#if property.columnType =='json'>
                ${property.columnName}=${r'#{'}record.${property.propertyName}${r', typeHandler=org.mybatis.plugin.JSONBinaryTypeHandler}'},
                <#elseif property.columnType =='jsonb'>
                ${property.columnName}=${r'#{'}record.${property.propertyName}${r', typeHandler=org.mybatis.plugin.JSONBinaryTypeHandler}'},
                <#else >
                ${property.columnName}=${r'#{'}record.${property.propertyName}${r'}'},
                </#if>
            </if>
        </#list>
        </set>
        <if test="_parameter != null">
            <include refid="Example_Where_Clause"/>
        </if>
    </update>

</mapper>
