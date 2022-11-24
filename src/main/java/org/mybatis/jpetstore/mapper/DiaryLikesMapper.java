package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Likes;

public interface DiaryLikesMapper {
    boolean didClickedLike(Likes likes);
}
