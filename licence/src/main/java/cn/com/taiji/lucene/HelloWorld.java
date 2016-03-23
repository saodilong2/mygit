package cn.com.taiji.lucene;

public class HelloWorld {

	public static void main(String[] args) {
		String indexDir = "C:\\��ϵͳ����\\lucene";
		String dataDir = "C:\\��ϵͳ����\\lucene\\data";
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
		System.out.println("������" + numIndexed + " ���ļ� ������" + (end - start) + " ����");

		//String indexDir = "C:\\��ϵͳ����\\lucene";
		String q = "Apache";
		try {
			Searcher.search(indexDir, q);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
