package com.serasa.common.utils.RunnerClass;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;

import com.serasa.common.utils.folder.FileManagerUtils;
import com.serasa.reports.ReportJson;

public class RESTRunner {
	
	public static void main(String[] args) throws IOException {
		//RUNNER CONFIGURATION
		String TEST_FOLDER = "com/serasa/features/";
		String TEMP_FOLDER = "features";
		String[] FEATURES = {"exemplo.feature"};
		String[] tags = {"~@ignore"};
		
		// generate report dir name
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String reportDirName = "Run " + sdf.format(date);
		String reportRoot = "../REPORTS/";
		String reportDir = reportRoot + reportDirName + "/";
		FileManagerUtils fUtils = new FileManagerUtils();
		
		fUtils.tempFeatureCreate(TEMP_FOLDER, TEST_FOLDER, FEATURES);
		
		String execFeaturesFolder = TEMP_FOLDER;
		
		String glue = "com.serasa.steps";

		String[] plugins = { "pretty", "json:" + reportDir + reportDirName + ".json" };
		String[] arguments = { "-m", "-p", plugins[0], "-p", plugins[1], "-g", glue,
								execFeaturesFolder };
		
		if ((args.length != 0) || (tags.length != 0)) {
			
			for (String arg : args) {
				tags = ArrayUtils.add(tags, arg);
			}
			
			for(String tag:tags) {
				arguments = ArrayUtils.add(arguments, "-t");
				arguments = ArrayUtils.add(arguments, tag);
			}
		}
		
		try {
			cucumber.api.cli.Main.run(arguments, Thread.currentThread().getContextClassLoader());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		ReportJson.generateFinalReport(reportDir, reportDir, "Report " + reportDirName);

		// abrir o relatório no final
		String reportAbsPath = new File(reportDir).getAbsolutePath();
		String report = reportAbsPath + "/cucumber-html-reports/overview-features.html";
		
		System.out.println("REPORT GENERATED: ");
		System.out.println(report);
		
		fUtils.tempDirectoryDelete(TEMP_FOLDER);
	}

}
