package input.builder;

import java.util.List;

import input.components.FigureNode;
import input.components.point.PointNode;
import input.components.point.PointNodeDatabase;
import input.components.segment.SegmentNode;
import input.components.segment.SegmentNodeDatabase;

public class GeometryBuilder extends DefaultBuilder {

	
	public GeometryBuilder() 
	{
		
	}

	@Override
    public FigureNode buildFigureNode(String description,
    		                          PointNodeDatabase points,
    		                          SegmentNodeDatabase segments)
    {
        return null;
    }
    
	@Override
    public SegmentNodeDatabase buildSegmentNodeDatabase()
    {
        return null;
    }
    
	@Override
    public void addSegmentToDatabase(SegmentNodeDatabase segments, PointNode from, PointNode to)
    {
    	if (segments != null) segments.addUndirectedEdge(from, to);
    }
    
	@Override
    public SegmentNode buildSegmentNode(PointNode pt1, PointNode pt2)
    {
        return null;
    }
    
	@Override
    public PointNodeDatabase buildPointDatabaseNode(List<PointNode> points)
    {
        return null;
    }
    
	@Override
    public PointNode buildPointNode(String name, double x, double y)
    {
        return null;
    }
	
}
