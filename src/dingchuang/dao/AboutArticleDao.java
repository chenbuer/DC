package dingchuang.dao;

import dingchuang.entity.AboutArticle;

public interface AboutArticleDao {
	void update(AboutArticle aboutArticle);
	AboutArticle getAbout(int id);
}
