package input.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import input.components.*;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNodeDatabase;
import input.exception.ParseException;

public class JSONParser
{
	protected ComponentNode  _astRoot;

	public JSONParser()
	{
		_astRoot = null;
	}

	private void error(String message)
	{
		throw new ParseException("Parse error: " + message);
	}

	public ComponentNode parse(String str) throws ParseException
	{
		// Parsing is accomplished via the JSONTokenizer class.
		JSONTokener tokenizer = new JSONTokener(str);
		JSONObject  JSONroot = (JSONObject)tokenizer.nextValue();

        // TODO: Build the whole AST, check for return class object, and return the root
		return null;
	}	
	
	private String parseDescription(String str) {
		//TODO parse the description from the JSON string and return description string
		return "TODO";
	}
	
	private PointNodeDatabase parsePointNodeDatabase(String str) {
		//TODO parse the JSON string and create pointNodeDatabase
		return null;
	}
	
	private SegmentNodeDatabase parseSegmentNodeDatabase(String str) {
		//TODO parse the JSON string and create segmentNodeDatabase 
		return null;
	}
	

    // TODO: implement supporting functionality
	
	 // TODO: implement supporting functionality
	
	
		//method for figurenode
		//method for pointnodedatabase
		//method for segmentnodedatabase
		//method for description
		
		
		
		
		
		public static void main(String[] args)
		{
			String filename = "JSON/collinear_line_segments.json";
			String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
			
			System.out.println(figureStr);
		}
	

}