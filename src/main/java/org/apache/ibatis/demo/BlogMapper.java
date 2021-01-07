package org.apache.ibatis.demo;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
  @Select("SELECT * FROM blog WHERE id = #{id}")
  Blog selectBlog(int id);
}
