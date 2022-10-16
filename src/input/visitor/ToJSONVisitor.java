package input.visitor;

import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;
import utilities.io.StringUtilities;

public class ToJSONVisitor implements ComponentNodeVisitor
{

	@Override
	public Object visitFigureNode(FigureNode node, Object o) {
		
		JSONObject result = new JSONObject(); 
		
		String description = node.getDescription();
		JSONObject descriptionJSON = new JSONObject();
		descriptionJSON.put("Description", description);
		
		result.put("Figure", descriptionJSON);
		
		SegmentNodeDatabase snDB = node.getSegments();
		
		visitSegmentDatabaseNode(snDB, null); 
		
		PointNodeDatabase pnDB = node.getPointsDatabase();
		
		visitPointNodeDatabase(pnDB, null); 
		
		
		return result; 
		
	}

		
	
	@Override
	public Object visitSegmentDatabaseNode(SegmentNodeDatabase node, Object o) {
		
		JSONArray segmentArray = new JSONArray(); 
		
		for (Map.Entry<PointNode, Set<PointNode>> key: node.asAdjList().entrySet()) {
			
			JSONObject segmentObject = new JSONObject();
						
			for (PointNode value: key.getValue()) {
				
				segmentObject.put(key.getKey().getName(), value.getName());
			}
			
			segmentArray.put(segmentObject); 
		}
		
		return segmentArray;
	}

	@Override
	public Object visitSegmentNode(SegmentNode node, Object o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitPointNode(PointNode node, Object o) {
		
		JSONObject pointJSON = new JSONObject();
		
		String name = node.getName(); //split into two steps for readability 
		Double x = node.getX();
		Double y = node.getY();
		
		pointJSON.put("name", name);
		pointJSON.put("x", x);
		pointJSON.put("y", y);
		
		return pointJSON; 
		
	}

	@Override
	public Object visitPointNodeDatabase(PointNodeDatabase node, Object o) {
		// TODO Auto-generated method stub
		
		JSONArray pointArray = new JSONArray(); 
		
		for(PointNode pn: node.getPointsSet())
		{
			pointArray.put(visitPointNode(pn, null));
		}
		
		return pointArray;
		
	}
	
}
