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


package edu.utep.trustlab.visko.web.requestHandler.queryExecution;

import javax.servlet.http.HttpServletRequest;

import edu.utep.trustlab.visko.sparql.UTEPProvenanceRDFStore;
import edu.utep.trustlab.visko.web.html.QueryMessages;
import edu.utep.trustlab.visko.web.html.QueryHTML;
import edu.utep.trustlab.visko.web.html.ResultsTableHTML;
import edu.utep.trustlab.visko.web.requestHandler.RequestHandlerHTML;
import edu.utep.trustlab.visko.execution.*;

public class ExecuteQueryServlet extends RequestHandlerHTML {

	private Query query;

	private void populateQueryForEntryPoint(HttpServletRequest request) {
		String artifactURL = request.getParameter("artifactURL");
		String viewerSetURI = request.getParameter("viewerSetURI");

		UTEPProvenanceRDFStore rdfStore = new UTEPProvenanceRDFStore();
		String formatURI = rdfStore.getFormatFromArtifactURL(artifactURL);
		String typeURI = rdfStore.getTypeFromArtifactURL(artifactURL);

		String stringQuery = "SELECT * IN-VIEWER " + viewerSetURI + " FROM "
				+ artifactURL + " FORMAT " + formatURI + " TYPE " + typeURI;

		query = new Query(stringQuery);
	}

	public String doGet(HttpServletRequest request){
		// TODO Auto-generated method stub
		String stringQuery = request.getParameter("query");

		if (stringQuery != null)
			query = new Query(stringQuery);
		else
			populateQueryForEntryPoint(request);

		System.out.println(query.getArtifactURL());
		System.out.println(query.getFormatURI());
		System.out.println(query.getTypeURI());
		System.out.println(query.getViewerSetURI());
		System.out.println(query.getViewURI());

		QueryEngine engine = new QueryEngine(query);

		String html = "<h2>VisKo Query</h2>";
		if (query.isValidQuery()) {
			// if valid query add the query engine to the session
			request.getSession().setAttribute("engine", engine);

			html += QueryHTML.getHTML(query);
			html += "<hr>";

			html += "<h2>Results</h2>";
			html += ResultsTableHTML.getHTML(engine, false);
			html += "<hr>";
		} else {
			html += "<p>Query is invalid!</p>";
			html += QueryHTML.getHTML(query);
			html += "<hr>";
		}

		String errors = QueryMessages.getQueryErrorsHTML(query);
		String warns = QueryMessages.getQueryWarningsHTML(query);

		if (errors != null || warns != null)
			html += "<h2>Messages</h2>";

		if (errors != null)
			html += "<div align=\"left\">" + errors + "</div>";
		if (warns != null)
			html += warns;

		return html;
	}
}