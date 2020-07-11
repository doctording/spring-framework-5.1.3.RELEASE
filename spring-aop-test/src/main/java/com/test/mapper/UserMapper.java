package com.test.mapper;

import com.test.entity.TbUser;
import org.apache.ibatis.annotations.Select;

/**
 * @Author mubi
 * @Date 2020/7/11 10:25
 */
public interface UserMapper {
	/**
	 * select 映射
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM t_user WHERE id = #{id}")
	TbUser selectUserById(int id);
}
