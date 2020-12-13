package com.esteelauder.top.mapper;

import com.esteelauder.top.example.ActressExample;
import com.esteelauder.top.model.Actress;
import com.esteelauder.top.parameter.Params;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * @author 墨茗琦妙
 */
@Mapper
public interface ActressMapper {

    @SelectProvider(type=ActressSqlProvider.class, method="countByExample")
    long countByExample(ActressExample example);

    @DeleteProvider(type=ActressSqlProvider.class, method="deleteByExample")
    int deleteByExample(ActressExample example);

    @Delete({
        "delete from actress",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into actress (id, name, ",
        "rank)",
        "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{rank,jdbcType=INTEGER})"
    })
    int insert(Actress record);

    @InsertProvider(type=ActressSqlProvider.class, method="insertSelective")
    int insertSelective(Actress record);

    @SelectProvider(type=ActressSqlProvider.class, method="selectByExample")
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType= JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType= JdbcType.VARCHAR),
        @Arg(column="rank", javaType=Integer.class, jdbcType= JdbcType.INTEGER)
    })
    List<Actress> selectByExample(ActressExample example);

    @Select({
        "select",
        "id, name, rank",
        "from actress",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType= JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType= JdbcType.VARCHAR),
        @Arg(column="rank", javaType=Integer.class, jdbcType= JdbcType.INTEGER)
    })
    Actress selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ActressSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Actress record, @Param("example") ActressExample example);

    @UpdateProvider(type=ActressSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Actress record, @Param("example") ActressExample example);

    @UpdateProvider(type=ActressSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Actress record);

    @Update({
        "update actress",
        "set name = #{name,jdbcType=VARCHAR},",
          "rank = #{rank,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Actress record);

    @Select("select * from actress where id = #{id}")
    Actress selectById(Integer id);

    @Select(value = "select * from actress where rank < #{rank} order by rank desc  limit 0,1")
    Actress previous(Integer rank);

    @Select(value = "select * from actress where rank  > #{rank} order by rank desc  limit 0,1")
    Actress next(Integer rank);

    /**
     *  查看最大的序号
     * @return Integer
     */
    @Select("SELECT rank as maxRank from actress ORDER BY actress.`rank` desc limit 1;")
    Integer maxRank();

    /**
     *  查看最小的序号
     * @return Integer
     */
    @Select("SELECT rank  as minRank from actress ORDER BY actress.`rank` asc limit 1;")
    Integer minRank();


}