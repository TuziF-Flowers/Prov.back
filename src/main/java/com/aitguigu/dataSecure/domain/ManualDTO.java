package com.aitguigu.dataSecure.domain;

//转化应用一级多表联查的数据类型
//从List<Object[]>转化为List<ManualDTO>
public class ManualDTO {
    private String activity;
    private String activityalias;
    private String activityname;
    private String xapplicationname;
    public ManualDTO(String activity,String activityalias,String activityname,String xapplicationname){
        this.activity=activity;
        this.activityalias=activityalias;
        this.activityname=activityname;
        this.xapplicationname=xapplicationname;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivityalias() {
        return activityalias;
    }

    public void setActivityalias(String activityalias) {
        this.activityalias = activityalias;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public String getXapplicationname() {
        return xapplicationname;
    }

    public void setXapplicationname(String xapplicationname) {
        this.xapplicationname = xapplicationname;
    }
}
