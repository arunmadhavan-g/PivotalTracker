package ford.pivotaltracker.analyze;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ford.pivotaltracker.beans.tracker.Story;

public class Analyzer {

	public Report aggregateStories(Map<String, Map<String, List<Story>>> map) {
		Report report = new Report();
		List<TagCounts> tags = new ArrayList<>();
		for(String tag: map.keySet()) {
			Map<String, List<Story>> fieldsMap = map.get(tag);
			TagCounts tagCount = new TagCounts();
			List<FieldCount> fieldList = new ArrayList<>(); 
			for(String field:fieldsMap.keySet()){
				FieldCount fieldCount = new FieldCount();
				List<Story> list = fieldsMap.get(field);
				fieldCount.setCount(list.size());
				fieldCount.setField(field);
				fieldList.add(fieldCount);
			}
			tagCount.setFieldCounts(fieldList);
			tagCount.setName(tag);
			tags.add(tagCount);
		}
		report.setAggregated(tags);
		
		return report;
	}

}
