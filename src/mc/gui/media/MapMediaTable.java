package mc.gui.media;

import java.util.concurrent.ConcurrentHashMap;

public class MapMediaTable {

	private ConcurrentHashMap<Integer, Integer> mapIndexToMediaId;
	private ConcurrentHashMap<Integer, String> mapIdToPath;
	
	public MapMediaTable() {
		mapIndexToMediaId = new ConcurrentHashMap<Integer, Integer>();
		mapIdToPath = new ConcurrentHashMap<Integer, String>();
	}
	
	public String getMediaPathByPosition(int index){
		return (mapIdToPath.get(mapIndexToMediaId.get(index)));
	}
	
	public void storeMedia(int index, int id, String path){
		mapIndexToMediaId.put(index, id);
		mapIdToPath.put(id, path);
	}
	
	public void clearAll(){
		mapIdToPath.clear();
		mapIndexToMediaId.clear();
	}
	
	public String debugDump(){
		StringBuilder sb = new StringBuilder();
		for(int index : mapIndexToMediaId.keySet()){
			sb.append(index + " : " + mapIndexToMediaId.get(index) + " : " + "(" + mapIdToPath.get(mapIndexToMediaId.get(index)) + ")");
			sb.append("\n");
		}
		return sb.toString();
	}
}
