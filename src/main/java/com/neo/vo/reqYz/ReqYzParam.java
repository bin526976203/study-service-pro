package com.neo.vo.reqYz;

/**
 * @author moxianbin on 2019/3/1.
 */
public class ReqYzParam {
    private String stuid;

    private String courseid;

    private String lessonid;

    /**
     * 算法组装
     */
    private String learnid;

    private int status;

    /**
     * 学习时间
     */
    private int duration;
    private int learntimeyx;

    /**
     * 时间格式：2019-02-27T16:46:55.544Z
     * btime 开始时间  intime 结束时间
     */
    private String btime;
    private String intime;
    private int pass;

    public ReqYzParam(String stuid, String classid, String courseid, String lessonid, String learnid, int status,
                      int duration, int learntimeyx, String btime, String intime, int pass) {
        this.classid = classid;
        this.stuid = stuid;
        this.courseid = courseid;
        this.lessonid = lessonid;
        this.learnid = learnid;
        this.status = status;
        this.duration = duration;
        this.learntimeyx = learntimeyx;
        this.btime = btime + "Z";
        this.intime = intime + "Z";
        this.pass = pass;
    }

    private String sname = "";

    private String idnum = "";

    private String fenyuanid = "yzyw";

    private String jobId = "";

    private String classid = "";



    private String ext="";
    private String fromip="";
    private int network = 11;
    private int syncstatus = 0;
    private String devicenum = "";
    private int percent = 0;
    private String fromcenter = "";
    private int coveragecount = 100;
    private String coverage = "";


    private int mode = 0;

    private int type = 0;

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getLessonid() {
        return lessonid;
    }

    public void setLessonid(String lessonid) {
        this.lessonid = lessonid;
    }

    public String getLearnid() {
        return learnid;
    }

    public void setLearnid(String learnid) {
        this.learnid = learnid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLearntimeyx() {
        return learntimeyx;
    }

    public void setLearntimeyx(int learntimeyx) {
        this.learntimeyx = learntimeyx;
    }

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getFenyuanid() {
        return fenyuanid;
    }

    public void setFenyuanid(String fenyuanid) {
        this.fenyuanid = fenyuanid;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFromip() {
        return fromip;
    }

    public void setFromip(String fromip) {
        this.fromip = fromip;
    }

    public int getNetwork() {
        return network;
    }

    public void setNetwork(int network) {
        this.network = network;
    }

    public int getSyncstatus() {
        return syncstatus;
    }

    public void setSyncstatus(int syncstatus) {
        this.syncstatus = syncstatus;
    }

    public String getDevicenum() {
        return devicenum;
    }

    public void setDevicenum(String devicenum) {
        this.devicenum = devicenum;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getFromcenter() {
        return fromcenter;
    }

    public void setFromcenter(String fromcenter) {
        this.fromcenter = fromcenter;
    }

    public int getCoveragecount() {
        return coveragecount;
    }

    public void setCoveragecount(int coveragecount) {
        this.coveragecount = coveragecount;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
