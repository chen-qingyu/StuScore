package stuscore.service.impl;

import stuscore.dao.ScoreDao;
import stuscore.dao.impl.ScoreDaoImpl;
import stuscore.entity.Score;
import stuscore.service.ScoreService;

/**
 * @Description: 学生成绩业务层实现类
 */
public class ScoreServiceImpl implements ScoreService
{

    private ScoreDao scoreDao = new ScoreDaoImpl();

    @Override
    public void add(Score score)
    {

        scoreDao.add(score);
    }

    @Override
    public void update(Score score)
    {

        scoreDao.update(score);
    }

    @Override
    public void delete(Score score)
    {

        scoreDao.delete(score);
    }

    @Override
    public void editScore(String[] sIds, String[] scores, int eId)
    {

        for (int i = 0; i < sIds.length; i++)
        {
            // DEBUG 修复不能清空成绩的bug
            int sId = Integer.valueOf(sIds[i]);
            double score = Double.valueOf(scores[i]);
            int flag = 0;
            if (scores[i] == null || scores[i].equals(""))
            {
                flag = 1;
            }

            Score temp = getScore(sId, eId);

            if (temp == null)
            {

                temp = new Score();
                temp.setEid(eId);
                temp.setSid(sId);
                temp.setScore(score);

                scoreDao.add(temp);
            }
            else if (flag == 1)
            {
                scoreDao.delete(temp);
            }
            else
            {
                temp.setScore(score);

                scoreDao.update(temp);
            }
        }

    }

    @Override
    public Score getScore(int sId, int eId)
    {

        Score score = scoreDao.qryScore(sId, eId);

        return score;
    }

    @Override
    public Score getOne(int id)
    {

        return null;
    }

}
