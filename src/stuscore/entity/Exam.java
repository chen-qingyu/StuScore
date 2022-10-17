package stuscore.entity;

/**
 * 考试信息实体类
 */
public class Exam
{
    private int id;

    private String name;

    private String eTime;

    private String intro;

    private int epId;

    private int etId;

    private int gId;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String geteTime()
    {
        return eTime;
    }

    public void seteTime(String eTime)
    {
        this.eTime = eTime;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public int getEpId()
    {
        return epId;
    }

    public void setEpId(int epId)
    {
        this.epId = epId;
    }

    public int getEtId()
    {
        return etId;
    }

    public void setEtId(int etId)
    {
        this.etId = etId;
    }

    public int getgId()
    {
        return gId;
    }

    public void setgId(int gId)
    {
        this.gId = gId;
    }

    @Override
    public String toString()
    {
        return "Exam [id=" + id + ", name=" + name + ", eTime=" + eTime + ", intro=" + intro + ", epId=" + epId
                + ", etId=" + etId + ", gId=" + gId + "]";
    }
}
