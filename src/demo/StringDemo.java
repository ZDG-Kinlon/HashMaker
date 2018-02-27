package cn.demo;

import cn.hash.Algorithm;
import cn.hash.MsgDigestString;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class StringDemo {

    /**
     * 文本的示例
     */
    @Test
    public void stringDemo() {
        //1.创建字符串
        String string = "数据摘要算法是密码学算法中非常重要的一个分支，它通过对所有数据提取指纹信息以实现数据签名、数据完整性校验等功能，由于其不可逆性，有时候会被用做敏感信息的加密。数据摘要算法也被称为哈希（Hash）算法或散列算法";
        //2.准备需要执行的方法集合，支持的类型在Algorithm接口中
        Set<String> set = Set.of(
                Algorithm.CRC32, Algorithm.CRC32C,
                Algorithm.MD2, Algorithm.MD5,
                Algorithm.SHA_1,
                Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384, Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256,
                Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384, Algorithm.SHA3_512
        );
        //3.创建摘要对象，传入摘要类型，大小写输出，文件
        MsgDigestString msgDigestString = new MsgDigestString(set, false, string);
        //4.设置字符串的字符集，默认utf-8，不支持的字符集也为utf-8
        msgDigestString.setCharset("big5");
        //5.获取结果
        Map<String, String> map = msgDigestString.hash();
        //6.输出
        map.forEach((key, value) -> System.out.println(key + "：" + value));
        string = msgDigestString.toString();//字符串的结果，包括文件路径，文件大小，和各文件摘要结果信息（无序），换行输出
        System.out.println(string);
    }
}
