/*
Copyright (c) 2012, University of Texas at El Paso
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted
provided that the following conditions are met:

	-Redistributions of source code must retain the above copyright notice, this list of conditions
	 and the following disclaimer.
	-Redistributions in binary form must reproduce the above copyright notice, this list of conditions
	 and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/


package edu.utep.trustlab.visko.knowledge.universal.profiles;

import edu.utep.trustlab.visko.knowledge.gmt.GMTServices;
import edu.utep.trustlab.visko.knowledge.ncl.NCLServices;
import edu.utep.trustlab.visko.ontology.service.writer.ToolkitProfileWriter;

public class GriddedGravityDataProfile {
	public static void create() {

		String region = "-109/-107/33/34";
		
		String gridded2Raster = GMTServices.gridded2Raster.substring(0, GMTServices.gridded2Raster.indexOf("#"));
		String gridded2ContourMap = GMTServices.gridded2ContourMap.substring(0, GMTServices.gridded2ContourMap.indexOf("#"));
		String gridded2RasterNCL = NCLServices.netCDFGridRaster.substring(0, NCLServices.netCDFGridRaster.indexOf("#"));
		String gridded2ContourMapNCL = NCLServices.netCDFGridContour.substring(0, NCLServices.netCDFGridContour.indexOf("#"));
		
		// gridded data
		String dataTypeURI = "http://rio.cs.utep.edu/ciserver/ciprojects/CrustalModeling/CrustalModeling.owl#d12";
		ToolkitProfileWriter wtr = new ToolkitProfileWriter("griddedGravityDataProfile");
		wtr.addDataType(dataTypeURI);
		
		// for GMT grdimage
		wtr.addInputBinding(gridded2Raster + "#B", "1");
		wtr.addInputBinding(gridded2Raster + "#J", "x4c");
		wtr.addInputBinding(gridded2Raster + "#C", "hot");
		wtr.addInputBinding(gridded2Raster + "#T", "-200/200/10");
		wtr.addInputBinding(gridded2Raster + "#R", region);
		
		// for GMT grdcontour
		wtr.addInputBinding(gridded2ContourMap + "#C", "10");
		wtr.addInputBinding(gridded2ContourMap + "#A", "20");
		wtr.addInputBinding(gridded2ContourMap + "#B", "0.5");
		wtr.addInputBinding(gridded2ContourMap + "#S", "5");
		wtr.addInputBinding(gridded2ContourMap + "#J", "x4c");
		wtr.addInputBinding(gridded2ContourMap + "#Wc", "thinnest,black");
		wtr.addInputBinding(gridded2ContourMap + "#Wa", "thinnest,black");

		// for NCL gsx_gsn something
		wtr.addInputBinding(gridded2ContourMapNCL + "#lbOrientation", "vertical");
		wtr.addInputBinding(gridded2ContourMapNCL + "#cnLevelSpacingF", "10");
		wtr.addInputBinding(gridded2ContourMapNCL + "#colorTable", "WhiteBlueGreenYellowRed");
		wtr.addInputBinding(gridded2ContourMapNCL + "#font", "helvetica");
		wtr.addInputBinding(gridded2ContourMapNCL + "#cnFillOn", "True");
		wtr.addInputBinding(gridded2ContourMapNCL + "#cnLinesOn", "False");
		wtr.addInputBinding(gridded2ContourMapNCL + "#latVariable", "y");
		wtr.addInputBinding(gridded2ContourMapNCL + "#lonVariable", "x");
		wtr.addInputBinding(gridded2ContourMapNCL + "#plotVariable", "z");
		wtr.addInputBinding(gridded2ContourMapNCL + "#indexOfX", "1");
		wtr.addInputBinding(gridded2ContourMapNCL + "#indexOfY", "0");
		wtr.addInputBinding(gridded2ContourMapNCL + "#indexOfZ", "-1");
		
		// for NCL gsx_gsn something excepted hard coded for raster generation
		wtr.addInputBinding(gridded2RasterNCL + "#lbOrientation", "vertical");
		wtr.addInputBinding(gridded2RasterNCL + "#colorTable", "WhiteBlueGreenYellowRed");
		wtr.addInputBinding(gridded2RasterNCL + "#font", "helvetica");
		wtr.addInputBinding(gridded2RasterNCL + "#latVariable", "y");
		wtr.addInputBinding(gridded2RasterNCL + "#lonVariable", "x");
		wtr.addInputBinding(gridded2RasterNCL + "#plotVariable", "z");
		wtr.addInputBinding(gridded2RasterNCL + "#indexOfX", "1");
		wtr.addInputBinding(gridded2RasterNCL + "#indexOfY", "0");
		wtr.addInputBinding(gridded2RasterNCL + "#indexOfZ", "-1");
		}
}
