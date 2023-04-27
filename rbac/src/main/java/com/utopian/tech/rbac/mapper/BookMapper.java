package com.utopian.tech.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.utopian.tech.rbac.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author aLiLang
 * @since 2023-02-09
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<Book> listForUpdate(List<Long> ids);

}
