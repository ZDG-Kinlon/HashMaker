# HashMaker
计算文件或者字符串的哈希值，支持CRC32、MD、SHA-1、SHA-2、SHA-3多种算法，借助缓冲区支持超过2G以上的大型文件，支持多文件多线程运算

使用示例：
/**
 * 一个文件执行的示例
 */
@Test
public void fileDemo1() {
	//1.创建文件对象
	File file = new File("D:\\1.png");
	//2.准备需要执行的方法集合，支持的类型在Algorithm接口中
	Set<String> set = Set.of(
			Algorithm.CRC32, Algorithm.CRC32C,
			Algorithm.MD2, Algorithm.MD5,
			Algorithm.SHA_1,
			Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384, Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256,
			Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384, Algorithm.SHA3_512
	);
	//3.创建摘要对象，传入摘要类型，大小写输出，文件
	MsgDigestFile msgDigestFile = new MsgDigestFile(set, false, file);
	//5.设置缓冲区的大小，单位字节，默认1MB，一次性读取1MB，分段处理大型文件
	msgDigestFile.setFileCacheSize(10485760);//10MB
	msgDigestFile.getFileSize();//文件的大小，单位字节，long
	msgDigestFile.getSurplusFileSize();//剩余未读取的字节大小，单位字节，long
	//6.执行，接收结果，key=算法类型，value=计算的摘要值
	Map<String, String> map = msgDigestFile.hash();
	//7.输出结果
	map.forEach((key, value) -> System.out.println(key + "：" + value));
	String string = msgDigestFile.toString();//字符串的结果，包括文件路径，文件大小，和各文件摘要结果信息（无序），换行输出
	System.out.println(string);
}

/**
 * 多文件，多线程的示例
 */
@Test
public void fileDemo2() {
	//1.创建文件集合
	List<File> fileList = List.of(
			new File("D:\\1.png"),
			new File("D:\\2.png"),
			new File("D:\\3.png")
	);
	//2.准备需要执行的方法集合，支持的类型在Algorithm接口中
	Set<String> set = Set.of(
			Algorithm.CRC32, Algorithm.CRC32C,
			Algorithm.MD2, Algorithm.MD5,
			Algorithm.SHA_1,
			Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384, Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256,
			Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384, Algorithm.SHA3_512
	);
	//3.创建多线程的摘要对象，传入摘要类型，大小写输出，文件list集合
	MsgDigestFiles msgDigestFiles = new MsgDigestFiles(set, false, fileList);
	//4.获取结果
	Map<File, Map<String, String>> map = msgDigestFiles.hash();
	//5.输出结果
	map.forEach((f, m) -> {
		System.out.println("文件：" + f.getAbsolutePath());
		System.out.println("大小：" + f.length());
		m.forEach((k, v) -> System.out.println(k + "：" + v));
	});
	System.out.println("======");
	String string = msgDigestFiles.toString();//字符串的结果，包括文件路径，文件大小，和各文件摘要结果信息（无序），换行输出
	System.out.println(string);
}


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
