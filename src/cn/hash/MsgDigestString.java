package cn.hash;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

public class MsgDigestString {
    //方法对象
    private Method method;

    //基础变量
    private Map<String, String> map;
    private StringBuffer sb;
    private boolean isLowerCase;

    //字符串
    private String string;
    //字符编码类型
    private String charset = "utf-8";

    public MsgDigestString(Set<String> set, boolean isLowerCase, String string) {
        this.isLowerCase = isLowerCase;
        this.string = string;

        this.sb = new StringBuffer();
        this.method = new Method(set);
        sb.append("文本：").append(string).append("\n");
        sb.append("字数：").append(string.length()).append(" 字节\n");
    }

    public Map<String, String> hash() {
        //初始化方法对象
        method.getMethod1().run();
        //创建字符流，自动关闭资源
        try {
            method.getMethod2().accept(string.getBytes(charset), string.length());
            //生成结果
            return this.map = method.getMethod3().apply(isLowerCase);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        map.forEach((k, v) -> sb.append(k).append("：").append(v).append("\n"));
        return sb.toString();
    }

    /**
     * 字符串编码，缺省为默认为utf-8，不识别的字符集默认utf-8
     *
     * @param charset
     */
    public void setCharset(String charset) {
        try {
            "".getBytes(charset);
            this.charset = charset;
        } catch (UnsupportedEncodingException e) {
            this.charset = "utf-8";
        }
    }
}
