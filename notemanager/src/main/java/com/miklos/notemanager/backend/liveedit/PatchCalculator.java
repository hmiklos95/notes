package com.miklos.notemanager.backend.liveedit;

import java.util.LinkedList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.miklos.notemanager.backend.liveedit.lib.diff_match_patch;

public class PatchCalculator {
	private static ExecutorService patchExecutor = Executors.newFixedThreadPool(16);
	
	private static CompletionService<LinkedList<diff_match_patch.Patch>> service = new ExecutorCompletionService<LinkedList<diff_match_patch.Patch>>(patchExecutor);
	
	private diff_match_patch diff = new diff_match_patch();
	
	
	public void submitPatchCalculation(String old, String updated) {
		
	}
	
	
	public LinkedList<diff_match_patch.Patch> calculateDiff(String old, String updated) throws InterruptedException, ExecutionException {
		return service.submit(()->{
			return diff.patch_make(diff.diff_main(old, updated));
		}).get();
	}
	
}
