/*
 *
 *  *
 *  * aFlux: JVM based IoT Mashup Tool
 *  * Copyright (C) 2018  Tanmaya Mahapatra
 *  *
 *  * This file is part of aFlux.
 *  *
 *  * aFlux is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * aFlux is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with aFlux.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */

package de.tum.in.aflux.component.trafficA9.actor;

import java.io.IOException;
import java.util.Map;


import de.tum.in.aflux.component.trafficA9.model.EvaluatorResult;
import de.tum.in.aflux.component.trafficA9.model.TitleResult;
import de.tum.in.aflux.flux_engine.FluxEnvironment;
import de.tum.in.aflux.flux_engine.FluxRunner;
import de.tum.in.aflux.tools.core.AbstractAFluxActor;

public class TrafficA9ReportActor extends AbstractAFluxActor{
	
	private static final String RESULTS_FILE_CSV =  "results.csv";
	private static final String OPEN_FILE_CSV =  "openGate.csv";
	private static final String CLOSE_FILE_CSV =  "closeGate.csv";
	
	
	public TrafficA9ReportActor(String fluxId, 
			FluxEnvironment fluxEnvironment, 
			FluxRunner fluxRunner,
			Map<String,String> properties) {
		super(fluxId, fluxEnvironment, fluxRunner,properties,0);
	}

	
	public TrafficA9ReportActor() {
		this("-1",null,null,null);
	}
	
	@Override
	protected void runCore(Object message) throws Exception {
		if (message instanceof TitleResult) {
			TitleResult titleMessage=(TitleResult) message;
			String stringMessage=titleMessage.getFirstColumn()+","+titleMessage.getSecondColumn()+"\n";
			this.appendToFile(titleMessage.getMainFolder(),titleMessage.getJobFolder(),RESULTS_FILE_CSV,stringMessage);
			this.appendToFile(titleMessage.getMainFolder(),titleMessage.getJobFolder(),OPEN_FILE_CSV,stringMessage);
			this.appendToFile(titleMessage.getMainFolder(),titleMessage.getJobFolder(),CLOSE_FILE_CSV,stringMessage);
		} else if (message instanceof EvaluatorResult) {
			EvaluatorResult resultMessage=(EvaluatorResult) message;
			String stringMessage=resultMessage.getOverallTicks()+","+resultMessage.getProduct()+"\n";
			this.sendOutput(stringMessage);
			this.appendToFile(resultMessage.getMainFolder(),resultMessage.getJobFolder(),RESULTS_FILE_CSV,stringMessage);

			stringMessage=resultMessage.getOverallTicks()+","+resultMessage.getOpens()+"\n";
			this.sendOutput(stringMessage);
			this.appendToFile(resultMessage.getMainFolder(),resultMessage.getJobFolder(),OPEN_FILE_CSV,stringMessage);

			stringMessage=resultMessage.getOverallTicks()+","+resultMessage.getCloses()+"\n";
			this.sendOutput(stringMessage);
			this.appendToFile(resultMessage.getMainFolder(),resultMessage.getJobFolder(),CLOSE_FILE_CSV,stringMessage);
			
			
			
			generateLocalChart(resultMessage.getMainFolder(),resultMessage.getJobFolder(),"rtxChart.html");
		}
	}
			
	
	private void generateLocalChart(String mainFolder, String jobFolder, String fileName) throws IOException {
		 String graphResult="";
			graphResult="<script src=\"https://www.amcharts.com/lib/3/amcharts.js\"></script>\n";
			graphResult+="<script src=\"https://www.amcharts.com/lib/3/xy.js\"></script>\n";
			graphResult+="<script src=\"https://www.amcharts.com/lib/3/plugins/export/export.min.js\"></script>\n";
			graphResult+="<link rel=\"stylesheet\" href=\"https://www.amcharts.com/lib/3/plugins/export/export.css\" type=\"text/css\" media=\"all\" />\n";
			graphResult+="<style>\n";
			graphResult+="#chartdiv {\n";
				graphResult+="width	: 100%;\n";
				graphResult+="height	: 500px;\n";
				graphResult+="}\n";		
				graphResult+="</style>\n";
				graphResult+="<script >\n";
				graphResult+="var chart = AmCharts.makeChart( \"chartdiv\", ";
				graphResult+=getDataReport(mainFolder,jobFolder);
				graphResult+="</script>\n";
				graphResult+="<script src=\"https://www.amcharts.com/lib/3/themes/light.js\"></script>\n";
				graphResult+="<div id=\"chartdiv\"></div>\n";
		this.generateChart(mainFolder,jobFolder,fileName,graphResult);
	}

	private String getDataReport(String mainFolder, String jobFolder) throws IOException {
		String[] csvFileContent=this.readTextFile(mainFolder,jobFolder,RESULTS_FILE_CSV);
		String[] csvOpenFileContent=this.readTextFile(mainFolder,jobFolder,OPEN_FILE_CSV);
		String[] csvCloseFileContent=this.readTextFile(mainFolder,jobFolder,CLOSE_FILE_CSV);

		
		
		String result="{\n";								
		result+="\"type\": \"xy\",\n";
				result+="\"theme\": \"light\",\n";
		
		result+="\"legend\": {\n";
		result+="\"horizontalGap\": 10,\n";
		result+="\"maxColumns\": 1,\n";
		result+="\"position\": \"right\",\n";
		result+="\"useGraphSettings\": true,\n";
		result+="\"markerSize\": 10\n";
		result+="},\n";
		
				result+="\"balloon\":{\n";
				result+="\"fixedPosition\":true,\n";
				result+="},\n";
				result+="\"dataProvider\": [ ";
		for (int i=1;i<csvFileContent.length;i++) {
			String s=csvFileContent[i];
			String[] values=s.split(",");
			String x=values[0];
			String y=values[1];
			result+="{\n";
			result+="\"y\": "+y+",\n";
			result+="\"x\": "+x+",\n";
			result+="\"value\": 10,\n";
			if (csvOpenFileContent.length>=i) {
				s=csvOpenFileContent[i];
				values=s.split(",");
				x=values[0];
				y=values[1];
				result+="\"y2\": "+y+",\n";
				result+="\"x2\": "+x+",\n";
				result+="\"value2\": 10,\n";
			}
			if (csvCloseFileContent.length>=i) {
				s=csvCloseFileContent[i];
				values=s.split(",");
				x=values[0];
				y=values[1];
				result+="\"y3\": "+y+",\n";
				result+="\"x3\": "+x+",\n";
				result+="\"value3\": 10,\n";
			}
			result+="}, ";
			
		}
				result+="  ],\n";
				result+="\"valueAxes\": [ {\n";
				result+="\"position\": \"bottom\",\n";
				result+="\"axisAlpha\": 0\n";
				result+="}, {\n";
				result+="\"minMaxMultiplier\": 1.2,\n";
				result+="\"axisAlpha\": 0,\n";
				result+="\"position\": \"left\",\n";
				result+="\"axisTitleOffset\":\"result\"\n";
				result+="} ],\n";
				result+="\"startDuration\": 1.5,\n";
		result+="\"graphs\": [ ";
		result+="{\n";
				result+="\"balloonText\": \"x:<b>[[x]]</b> y:<b>[[y]]</b><br>value:<b>[[value]]</b>\",\n";
				result+="\"bullet\": \"circle\",\n";
				result+="\"bulletBorderAlpha\": 0.2,\n";
				result+="\"bulletAlpha\": 0.8,\n";
				result+="\"lineAlpha\": 0,\n";
				result+="\"fillAlphas\": 0,\n";
				result+="\"valueField\": \"value\",\n";
				result+="\"xField\": \"x\",\n";
				result+="\"yField\": \"y\",\n";
		result+="\"title\": \"Occupancy\",\n";
				result+="\"maxBulletSize\": 15\n";
		result+="},\n";

		
		result+="{\n";
		result+="\"balloonText\": \"x:<b>[[x]]</b> y:<b>[[y]]</b><br>value:<b>[[value]]</b>\",\n";
		result+="\"bullet\": \"triangleUp\",\n";
		result+="\"bulletBorderAlpha\": 0.2,\n";
		result+="\"bulletAlpha\": 0.8,\n";
		result+="\"lineAlpha\": 0,\n";
		result+="\"fillAlphas\": 0,\n";
		result+="\"valueField\": \"value2\",\n";
		result+="\"xField\": \"x2\",\n";
		result+="\"yField\": \"y2\",\n";
		result+="\"title\": \"Open Gate\",\n";
		result+="\"maxBulletSize\": 15\n";
		result+="},\n";
		
		result+="{\n";
		result+="\"balloonText\": \"x:<b>[[x]]</b> y:<b>[[y]]</b><br>value:<b>[[value]]</b>\",\n";
		result+="\"bullet\": \"triangleDown\",\n";
		result+="\"bulletBorderAlpha\": 0.2,\n";
		result+="\"bulletAlpha\": 0.8,\n";
		result+="\"lineAlpha\": 0,\n";
		result+="\"fillAlphas\": 0,\n";
		result+="\"valueField\": \"value3\",\n";
		result+="\"xField\": \"x3\",\n";
		result+="\"yField\": \"y3\",\n";
		result+="\"title\": \"Close Gate\",\n";
		result+="\"maxBulletSize\": 15\n";
		result+="},\n";
		
		
		result+="],\n";
		
		
				result+="\"marginLeft\": 46,\n";
				result+="\"marginBottom\": 35,\n";
				result+="\"export\": {\n";
				result+="\"enabled\": true\n";
				result+="}\n";
				result+="} );\n";

		return result;
	}

}
