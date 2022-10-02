package input.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
		JSONObject figure = JSONroot.getJSONObject("Figure");
				
		String description = parseDescription(figure); 
		PointNodeDatabase pointDB = parsePointNodeDatabase(figure);
		SegmentNodeDatabase segmentDB = parseSegmentNodeDatabase(figure);
		
		
		return null;
		
		

        // TODO: Build the whole AST, check for return class object, and return the root
	}	
	
	private String parseDescription(JSONObject json) {

		return json.get("Description").toString(); 
	}
	
	private PointNodeDatabase parsePointNodeDatabase(JSONObject json) {
		
		PointNodeDatabase PointNodeDB = new PointNodeDatabase(); 
		JSONArray arry = json.getJSONArray("Points");
		
		for(Object item: arry)
		{
			JSONObject currentNode = (JSONObject) item;
			String name = currentNode.getString("name"); 
			Integer x = currentNode.getInt("x"); 
			Integer y = currentNode.getInt("y");
		}
		//TODO create pointNodeDatabase
		
		return null;
		
	}
	/**
	 * Takes a JSON pointNode String as input and finds 
	 * and returns a new pointNode with that name/coords
	 * @param String key
	 * @return a new PointNode
	 */
	private PointNode getPointNode (String key, JSONObject json) {
		JSONArray points = json.getJSONArray("Points");
		//find input key
		for (Object point:points) {
			JSONObject currentPoint = (JSONObject) point;
			if (currentPoint.get("name").equals(key)) {
				double x = currentPoint.getDouble("x");
				double y = currentPoint.getDouble("y");
				return new PointNode(key, x, y);
			}
		}
		return null;
	}
	/**
	 * Parse the Figure's segment input and create a new segmentNode Database
	 * @param JSON figure
	 * @return a corresponding SegmentNodeDatabase
	 */
	private SegmentNodeDatabase parseSegmentNodeDatabase(JSONObject json) {
		//parse the JSON string and create segmentNodeDatabase
		List<PointNode> points = new ArrayList<PointNode>();
		SegmentNodeDatabase segmentDB = new SegmentNodeDatabase();
		JSONArray segments = json.getJSONArray("Segments");
		JSONArray pointsArray = null;
		JSONObject currentAdjList = null;
		String currentPoint, key = null;
		PointNode pointA, pointB = null;

		//Loop through each AdjList and create points
		for (Object adjList: segments) {
			//get the adjList String key and create a new pointNodeA
			currentAdjList = (JSONObject) adjList;
			key = currentAdjList.keySet().iterator().next();
			pointA = getPointNode(key, json);
			//create array of pointNode Strings
			pointsArray = currentAdjList.getJSONArray(key);
			for (Object segmentPoint: pointsArray) {
				//create new pointNodeBs and add them to points list
				currentPoint = (String) segmentPoint;
				pointB = getPointNode(currentPoint, json);
				points.add(pointB);
				System.out.println(points); //TODO testing output
			}
			//add new adjList with pointA and pointsB
			segmentDB.addAdjacencyList(pointA, points);
			points.clear();
		}
		System.out.println(segmentDB.asUniqueSegmentList()); //TODO testing output
		return segmentDB;
	}
	

    // TODO: implement supporting functionality
	
	 // TODO: implement supporting functionality
	
	
		//method for figurenode
		//method for pointnodedatabase
		//method for segmentnodedatabase
		//method for description
		
		
		
		/*
		public static void main(String[] args)
		{
			String filename = "JSON/collinear_line_segments.json";
			String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
			
			System.out.println(figureStr);
			System.out.println(parse(figureStr)); 
		}
		*/
	

}