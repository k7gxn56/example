package hello;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 高德地图API返回的POI对象
 * 字段的含义请参数文档:https://lbs.amap.com/api/webservice/guide/api/search
 * @author shenxiang
 * @createDate 2020-03-09 17:22 [星期一]
 */

public class GaoDePoi implements Serializable {

    private static final long serialVersionUID = 705180646719838197L;

    private String id;

    private String name;

    private List tag;

    private String type;

    private Integer typecode;

    private String address;

    private String location;

    private Object tel;

    private Object website;

    private String pcode;

    private String pname;

    private String citycode;

    private String cityname;

    private String adcode;

    private String adname;

    private Object alias;

    private List photos;

    public GaoDePoi(){
        System.out.println("GaoDePoi()");
    }

    public GaoDePoi(String id){
        System.out.println("GaoDePoi(String id)");
    }

    public GaoDePoi(String id,String name){
        System.out.println("GaoDePoi(String id,String name)");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getTag() {
        return tag;
    }

    public void setTag(List tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypecode() {
        return typecode;
    }

    public void setTypecode(Integer typecode) {
        this.typecode = typecode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Object getTel() {
        return tel;
    }

    public void setTel(Object tel) {
        this.tel = tel;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public Object getAlias() {
        return alias;
    }

    public void setAlias(Object alias) {
        this.alias = alias;
    }

    public List getPhotos() {
        return photos;
    }

    public void setPhotos(List photos) {
        this.photos = photos;
    }
}
