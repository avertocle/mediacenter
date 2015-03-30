package mc.model.movie;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import mc.explorer.MediaFinder;
import mc.explorer.MediaFinderWindows;
import mc.utils.Logger;

public class Test {

	public static void mainw(String[] args) {
		Test test = new Test();
		List<String> list = test.loadLibraryInModel();
		test.execute(list);
	}
	
	private List<String> loadLibraryInModel(){
		MediaFinder mediaFinder = new MediaFinderWindows();
		List<File> allFileList = new ArrayList<File>();
		allFileList.addAll(mediaFinder.findAllMediaInDir("H:\\1_Movies"));

		List<String> list = new ArrayList<String>();
		for(File file : allFileList){
			list.add(file.getName());
		}
		return list;
	}
	
	private void execute(final List<String> list){
		
		Thread t = new Thread(){
			@Override
			public void run() {
				RtMovieInfoFetcher rt = new RtMovieInfoFetcher();
				for(String n : list){
					Logger.log(n + "   ::: " + rt.fetchInfo(n).name);
					try {
						sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		t.start();
	}

}
