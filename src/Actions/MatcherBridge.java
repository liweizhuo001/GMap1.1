/*******************************************************************************
 * Copyright 2015 by Academy of Mathematics and Systems Science Chinese Academy of Sciences(University of Chinese Academy of Sciences)
 * 
 *  GMap1.0 is a tool for ontology matching, which combines the sum-roduct networks and noisy-or model. 
 * 
 *   Author:  Weizhuo Li & Qilin Sun  eMail: liweizhuo2014@gmail.com
 * 
 ******************************************************************************/
package Actions;


import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.semanticweb.owlapi.model.IRI;

import eu.sealsproject.platform.res.domain.omt.IOntologyMatchingToolBridge;
import eu.sealsproject.platform.res.tool.api.ToolBridgeException;
import eu.sealsproject.platform.res.tool.api.ToolException;
import eu.sealsproject.platform.res.tool.api.ToolType;
import eu.sealsproject.platform.res.tool.impl.AbstractPlugin;

//import uk.ac.ox.krr.logmap2.*;


public class MatcherBridge extends AbstractPlugin implements IOntologyMatchingToolBridge {

	/**
	* LogMap 2 aligns two ontologies specified via their URL and returns the 
	* URL of the resulting alignment, which should be stored locally.
	* 
	*/
	public URL align(URL source, URL target) throws ToolBridgeException, ToolException {
		GMap_D gmap;
		URL url_alignment;
		long init, fin;
		
		init = Calendar.getInstance().getTimeInMillis();
		
		try {
		
			gmap =new GMap_D();
			/*System.out.println("**************************");
			System.out.println(source);
			System.out.println(target);
			System.out.println("**************************");*/
			
			//logmap.align(source, target);
			gmap.init(source, target);
			
			url_alignment=gmap.returnAlignmentFile();
			
			gmap.SaveAligenmt();
			
			fin = Calendar.getInstance().getTimeInMillis();
			//System.out.println("Total time LogMap (s): " + (float)((double)fin-(double)init)/1000.0);
		
			return url_alignment;
			
		}
		
		catch (IOException e) {
			throw new ToolBridgeException("Cannot create file for resulting alignment", e);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ToolException("Error extracting/cleaning/storing mappings with GMap: ");
			
		}
	}

	/**
	* Not considered in LogMap
	*/
	public URL align(URL source, URL target, URL inputAlignment) throws ToolBridgeException, ToolException {
		throw new ToolException("functionality of called method is not supported");
	}

	/**
	* No pre-resiquites in LogMap
	*/
	public boolean canExecute() {
		return true;
	}

	/**
	* The LogMap is an ontology matching tool. 
	*/
	public ToolType getType() {
		return ToolType.OntologyMatchingTool;
	}
	
	
	
	public static void main(String[] args) {
		MatcherBridge m = new MatcherBridge();
		
		//Parameters.print_output=true;
		
	/*	String uri1 = "http://repositories.seals-project.eu/tdrs/testdata/persistent/2015benchmarks-biblio/2015benchmarks-biblio-r1/suite/201/component/source/";
		String uri2 = "http://repositories.seals-project.eu/tdrs/testdata/persistent/2015benchmarks-biblio/2015benchmarks-biblio-r1/suite/201/component/target/";*/
		
		/*String uri1="http://repositories.seals-project.eu/tdrs/testdata/persistent/2015benchmarks-ifc/2015benchmarks-ifc-r1/suite/101/component/source/";
		String uri2="http://repositories.seals-project.eu/tdrs/testdata/persistent/2015benchmarks-ifc/2015benchmarks-ifc-r1/suite/101/component/target/";*/
		
		/*String uri1="http://repositories.seals-project.eu/tdrs/testdata/persistent/conference/conference-v1/suite/cmt-ekaw/component/source/";
		String uri2="http://repositories.seals-project.eu/tdrs/testdata/persistent/conference/conference-v1/suite/cmt-ekaw/component/target/";*/
			
		/*String uri1="http://repositories.seals-project.eu/tdrs/testdata/persistent/anatomy_track/anatomy_track-anatomy_2015/suite/mouse-human-suite/component/source/";
		String uri2="http://repositories.seals-project.eu/tdrs/testdata/persistent/anatomy_track/anatomy_track-anatomy_2015/suite/mouse-human-suite/component/target/";*/
			
		/*String uri1="http://repositories.seals-project.eu/tdrs/testdata/persistent/largebio/largebio-fma_nci_small_2015/suite/small-fma-nci/component/source/";
		String uri2="http://repositories.seals-project.eu/tdrs/testdata/persistent/largebio/largebio-fma_nci_small_2015/suite/small-fma-nci/component/target/";*/
		
		/*String uri1 = "file:Ontologies/101onto.rdf";
		String uri2 = "file:Ontologies/201-2onto.rdf";*/	
		
		/*String uri1 = "file:Ontologies/mouse.owl";
		String uri2 = "file:Ontologies/human.owl";*/
		
		String uri1=args[0]; 
		String uri2=args[1]; 
		
		
		try {
			m.align(IRI.create(uri1).toURI().toURL(), IRI.create(uri2).toURI().toURL());
		} catch (ToolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ToolBridgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
