package org.mybatis.jpetstore.mapper;

import org.mybatis.jpetstore.domain.Likes;

public interface DiaryLikesMapper {
    int didClickedLike(Likes likes);
}
