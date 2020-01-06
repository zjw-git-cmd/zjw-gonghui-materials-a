package com.gh.util.api;

import com.gh.db.PageUtils;
import com.gh.util.JacksonMapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 封装返回后的信息,状态码,消息,id和URL
 * @param <T>
 */
public class R<T> extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer code;
    private String msg;
    private String id;
    private String url;
    //private Long count;
    @SuppressWarnings("rawtypes")
    private PageUtils page;
    private T data;

    //初始化状态码
    public R() {
        this.setCode(200);
    }

    /**
     * 是否成功！
     * @return
     */
    public boolean isOK(){
        code =  this.getCode();
        if (0 == code.intValue() || 200 == code.intValue()){
            return true;
        }else{
            return false;
        }
    }

    //返回封装500 的消息
    public static <T> R<T> error(String msg) {
        R<T> r = new R<T>();
        r.setCode(500);
        r.setMsg(msg);
        return r;

    }

    //返回URL地址
    public R<T> url(String url) {
        this.setUrl(url);
        return this;
    }
    //
    public R<T> err(String msg) {
        this.setCode(500);
        this.setMsg(msg);
        return this;
    }


    /**
     *
     * @param code   自定义状态码
     * @param msg    自定义错误消息
     * @return
     */
    public R<T> err(Integer code ,String msg) {
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public int getCode(){
        this.code =  (Integer) super.get("code");
        return this.code;
    }


    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<T>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> error(int code, String msg,String url) {
        R<T> r = new R<T>();
        r.setCode(code);
        r.setMsg(msg);
        r.setUrl(url);
        return r;
    }


    public static <T> R<T> error(String code,String msg) {
        R<T> r = new R<T>();
        r.setMsg(msg);
        r.setCode(Integer.parseInt(code));

        return r;
    }


    public static R<String> ok(String msg) {
        R<String> r = new R<String>();
        r.setCode(200);
        r.setMsg(msg);
        r.setData(msg);
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<T>();
        r.setCode(200);
        return r.data(data);
    }

    public static <K> R<PageUtils<K>> ok(PageUtils<K> page) {
        R<PageUtils<K>> r = new R<PageUtils<K>>();
        r.setCode(200);
        return r.page(page);
    }


    public R<T> data(T data) {
        this.setData(data);

        return this;
    }

    public <K> R<T> page(PageUtils<K> page) {
        this.setPage(page);
        return this;
    }


    //返回状态码
    public static <T> R<T> ok() {
        R<T> r = new R<T>();
        r.setCode(200);
        return r;
    }


    @Deprecated
    public Object get(Object k){
        String key = k.toString();
        if (key.equalsIgnoreCase("data")){
            return this.getData();
        }

        if (key.equalsIgnoreCase("url")){
            return this.getUrl();
        }

        if (key.equalsIgnoreCase("msg")){
            return this.getMsg();
        }

        if (key.equalsIgnoreCase("id")){
            return this.getId();
        }

        return super.get(k);
    }

    @Deprecated
    public R<T> put(Integer key, Object value) {
        return put(key.toString(),value);
    }

    @Deprecated
    public R<T> put(Long key, Object value) {
        return put(key.toString(),value);
    }

    @Deprecated
    public R<T> put(long key, Object value) {
        return put(String.valueOf(key),value);
    }

    @Deprecated
    public R<T> put(int key, Object value) {
        return put(String.valueOf(key),value);
    }

    @Deprecated
    public R<T> put(BigInteger key, Object value) {
        return put(key.toString(),value);
    }


    @Deprecated
    public  R<T> put(String key, Object value) {
        if (key.equalsIgnoreCase("data")){
            this.data = (T) value;
        }

        if (key.equalsIgnoreCase("url")){
            this.url = value.toString();
        }

        if (key.equalsIgnoreCase("msg")){
            this.msg =  value.toString();
        }

        if (key.equalsIgnoreCase("id")){
            this.id = value.toString();
        }

        if (key.equalsIgnoreCase("page")){
            if (value instanceof  String){
                PageUtils<?> p = JacksonMapper.jsonToBean(value.toString(), PageUtils.class);
                this.page = p;
            }else if  (value instanceof LinkedHashMap
					/*	|| value instanceof TreeMap
						|| value instanceof LinkedTreeMap
						|| value instanceof Map*/
            ){
                PageUtils<?> p = ParameterUtil.mapToBean(value, PageUtils.class);
                this.page = p;
            }else{
                this.page = (PageUtils<?>)  value;
            }
            super.put("page", this.page);
        }

        super.put(key, value);
        return this;
    }



    public String getMsg() {
        this.msg =  super.get("msg").toString();
        return msg;
    }

    public String getUrl() {
        this.url = super.get("url").toString();
        return url;
    }




    /**
     * 得到"data"无素x
     */
    public T getData(){
        Object vo = super.get("data");
        if (vo!=null){
            data = (T) vo;
        }
        return data;

    }

    public <K> T getData(Class<K> clz){
        Object vo = super.get("data");
        if (vo==null ){
            return null;
        }

        if (vo instanceof List<?>){
            List<K> t= ParameterUtil.toListBean(vo,clz);
            return (T) t;
        }else if (vo instanceof LinkedHashMap
				/*|| vo instanceof TreeMap
				|| vo instanceof LinkedTreeMap
				|| vo instanceof Map*/
        ){
            //T tt = JacksonMapper.mapToBean(vo, clz);
            T tt = (T) ParameterUtil.mapToBean(vo, clz);
            return tt;
        } else if(vo instanceof BigDecimal || vo instanceof Integer || vo instanceof Long){
            T tt = (T) JacksonMapper.jsonToBean(String.valueOf(vo), clz);
            return tt;
        } else if(vo instanceof String){
            T tt = (T) JacksonMapper.jsonToBean((String) vo, clz);
            return tt;
        }
        return data;
    }



    public String getId(){
        //this.id = super.get("id").toString();
        return id;
    }


    public <K> PageUtils<K> getPage() {
        Object value = super.get("page");
        if (value instanceof  String){
            PageUtils<?> p = JacksonMapper.jsonToBean(value.toString(), PageUtils.class);
            this.page =  p;
        }else if (value instanceof LinkedHashMap){
            PageUtils<?> p = ParameterUtil.mapToBean(value, PageUtils.class);
            this.page = p;
        }else{
            this.page = (PageUtils<K>) value;
        }
        return (PageUtils<K>) page;
    }


    public <K> PageUtils<K> getPage(Class<K> clz) {
        PageUtils<K> page = getPage();
        List<K> k = page.getList(clz);
        page.setList(k);
        return page;
    }



    public R<T> setData(T data){
        this.data = data;
        super.put("data", this.data);
        return this;
    }


    public void setId(String id){
        this.id=id;
        super.put("id", this.id);
    }

    public void setMsg(String msg) {
        this.msg = msg;
        super.put("msg", this.msg);
    }


    public void setCode(Integer code) {
        this.code = code;
        super.put("code",this.code);
    }

    public void setUrl(String url){
        this.url = url;
        super.put("url",this.url);
    }

	/*public Long getCount() {
		this.count =  Long.valueOf(super.get("count").toString());
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
		super.put("count",this.count);
	}*/

    /**
     *
     * @param <K>
     * @param page
     */
    public <K> void setPage(PageUtils<K> page) {
        this.page =  (PageUtils<K>) page;
        super.put("page", page);
    }




    @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
    public static void main(String[] arg){
//		R r1 = new R();
//		r1.setCode(444);
//		r1.setMsg("300l");
//		r1.setData("aaa");
//		r1.setId("fff");
//		r1.setUrl("ff");
//		r1.setPage(null);
//
//		String json = JacksonMapper.beanToJson(r1);
//
//		System.out.println("json==="+json);
//		R<String> r = JacksonMapper.jsonToBean(json, R.class);
//
//
//		String json1 = JacksonMapper.beanToJson(R.error(503,"登出！"));
//		System.out.println(json1);
        BigDecimal bd = new BigDecimal(100.0);
        R<BigDecimal> r = R.ok(bd);

        BigDecimal t = r.getData(BigDecimal.class);
        System.out.println(t);
    }
}
