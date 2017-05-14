package springbackend.spizheno;

public class ExtensionIndexer implements Indexer {

	public Index createIndex(String[] dictionary) {
		return new ExtensionIndex(dictionary);
	}
}
