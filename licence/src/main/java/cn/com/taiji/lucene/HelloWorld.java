package cn.com.taiji.lucene;

public class HelloWorld {

	public static void main(String[] args) {
		String indexDir = "C:\\非系统程序\\lucene";
		String dataDir = "C:\\非系统程序\\lucene\\data";
		Indexer indexer = null;
		int numIndexed = 0;
		long start = System.currentTimeMillis();
		try {
			indexer = new Indexer(indexDir);
			numIndexed = indexer.index(dataDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				indexer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("索引：" + numIndexed + " 个文件 花费了" + (end - start) + " 毫秒");

		//String indexDir = "C:\\非系统程序\\lucene";
		String q = "Apache";
		try {
			Searcher.search(indexDir, q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
