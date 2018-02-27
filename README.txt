# HashMaker
�����ļ������ַ����Ĺ�ϣֵ��֧��CRC32��MD��SHA-1��SHA-2��SHA-3�����㷨������������֧�ֳ���2G���ϵĴ����ļ���֧�ֶ��ļ����߳�����

ʹ��ʾ����
/**
 * һ���ļ�ִ�е�ʾ��
 */
@Test
public void fileDemo1() {
	//1.�����ļ�����
	File file = new File("D:\\1.png");
	//2.׼����Ҫִ�еķ������ϣ�֧�ֵ�������Algorithm�ӿ���
	Set<String> set = Set.of(
			Algorithm.CRC32, Algorithm.CRC32C,
			Algorithm.MD2, Algorithm.MD5,
			Algorithm.SHA_1,
			Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384, Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256,
			Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384, Algorithm.SHA3_512
	);
	//3.����ժҪ���󣬴���ժҪ���ͣ���Сд������ļ�
	MsgDigestFile msgDigestFile = new MsgDigestFile(set, false, file);
	//5.���û������Ĵ�С����λ�ֽڣ�Ĭ��1MB��һ���Զ�ȡ1MB���ֶδ�������ļ�
	msgDigestFile.setFileCacheSize(10485760);//10MB
	msgDigestFile.getFileSize();//�ļ��Ĵ�С����λ�ֽڣ�long
	msgDigestFile.getSurplusFileSize();//ʣ��δ��ȡ���ֽڴ�С����λ�ֽڣ�long
	//6.ִ�У����ս����key=�㷨���ͣ�value=�����ժҪֵ
	Map<String, String> map = msgDigestFile.hash();
	//7.������
	map.forEach((key, value) -> System.out.println(key + "��" + value));
	String string = msgDigestFile.toString();//�ַ����Ľ���������ļ�·�����ļ���С���͸��ļ�ժҪ�����Ϣ�����򣩣��������
	System.out.println(string);
}

/**
 * ���ļ������̵߳�ʾ��
 */
@Test
public void fileDemo2() {
	//1.�����ļ�����
	List<File> fileList = List.of(
			new File("D:\\1.png"),
			new File("D:\\2.png"),
			new File("D:\\3.png")
	);
	//2.׼����Ҫִ�еķ������ϣ�֧�ֵ�������Algorithm�ӿ���
	Set<String> set = Set.of(
			Algorithm.CRC32, Algorithm.CRC32C,
			Algorithm.MD2, Algorithm.MD5,
			Algorithm.SHA_1,
			Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384, Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256,
			Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384, Algorithm.SHA3_512
	);
	//3.�������̵߳�ժҪ���󣬴���ժҪ���ͣ���Сд������ļ�list����
	MsgDigestFiles msgDigestFiles = new MsgDigestFiles(set, false, fileList);
	//4.��ȡ���
	Map<File, Map<String, String>> map = msgDigestFiles.hash();
	//5.������
	map.forEach((f, m) -> {
		System.out.println("�ļ���" + f.getAbsolutePath());
		System.out.println("��С��" + f.length());
		m.forEach((k, v) -> System.out.println(k + "��" + v));
	});
	System.out.println("======");
	String string = msgDigestFiles.toString();//�ַ����Ľ���������ļ�·�����ļ���С���͸��ļ�ժҪ�����Ϣ�����򣩣��������
	System.out.println(string);
}


/**
 * �ı���ʾ��
 */
@Test
public void stringDemo() {
	//1.�����ַ���
	String string = "����ժҪ�㷨������ѧ�㷨�зǳ���Ҫ��һ����֧����ͨ��������������ȡָ����Ϣ��ʵ������ǩ��������������У��ȹ��ܣ������䲻�����ԣ���ʱ��ᱻ����������Ϣ�ļ��ܡ�����ժҪ�㷨Ҳ����Ϊ��ϣ��Hash���㷨��ɢ���㷨";
	//2.׼����Ҫִ�еķ������ϣ�֧�ֵ�������Algorithm�ӿ���
	Set<String> set = Set.of(
			Algorithm.CRC32, Algorithm.CRC32C,
			Algorithm.MD2, Algorithm.MD5,
			Algorithm.SHA_1,
			Algorithm.SHA_224, Algorithm.SHA_256, Algorithm.SHA_384, Algorithm.SHA_512, Algorithm.SHA_512_224, Algorithm.SHA_512_256,
			Algorithm.SHA3_224, Algorithm.SHA3_256, Algorithm.SHA3_384, Algorithm.SHA3_512
	);
	//3.����ժҪ���󣬴���ժҪ���ͣ���Сд������ļ�
	MsgDigestString msgDigestString = new MsgDigestString(set, false, string);
	//4.�����ַ������ַ�����Ĭ��utf-8����֧�ֵ��ַ���ҲΪutf-8
	msgDigestString.setCharset("big5");
	//5.��ȡ���
	Map<String, String> map = msgDigestString.hash();
	//6.���
	map.forEach((key, value) -> System.out.println(key + "��" + value));
	string = msgDigestString.toString();//�ַ����Ľ���������ļ�·�����ļ���С���͸��ļ�ժҪ�����Ϣ�����򣩣��������
	System.out.println(string);
}