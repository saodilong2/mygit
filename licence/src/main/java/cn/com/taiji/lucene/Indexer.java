package cn.com.taiji.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {

	private IndexWriter indexWriter; //写入索引
	
	/**
	 * 实例化indexWriter
	 * @param dir
	 * @throws Exception
	 */
	public Indexer(String dir) throws Exception	{
		Directory directory = FSDirectory.open(Paths.get(dir));
		Analyzer analyzer = new StandardAnalyzer(); //分词器
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		indexWriter = new IndexWriter(directory, conf);
	}
	
	public void close() throws IOException{
		indexWriter.close();
	}
	
	public int index(String dataDir) throws Exception{
		File file[] = new File(dataDir).listFiles();
		for(File f : file){
			indexFile(f);
		}
		return indexWriter.numDocs();
	}

	private void indexFile(File f) throws Exception {
		Document document = getDocument(f);
		indexWriter.addDocument(document);
	}

	private Document getDocument(File f) throws Exception{
		Document document = new Document();
		document.add(new TextField("content", new FileReader(f)));
		document.add(new TextField("filename", f.getName(),Field.Store.YES));
		document.add(new TextField("fullPath",f.getCanonicalPath(),Field.Store.YES));
		return document;
	}
}
