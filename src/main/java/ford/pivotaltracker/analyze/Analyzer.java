package ford.pivotaltracker.analyze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import ford.pivotaltracker.beans.tracker.Story;

public class Analyzer {

	public static final Function<Map<String, Map<String, Map<String, List<Story>>>>, Map<String, Report>> aggregateStoryCount = map ->  
	 {
		Map<String, Report> reportMap = new HashMap<>();
		for(String project: map.keySet()){
			Map<String, Map<String, List<Story>>> projectMap = map.get(project);
			Report report = new Report();
			List<TagCounts> tags = new ArrayList<>();
			for(String tag: projectMap.keySet()) {
				Map<String, List<Story>> fieldsMap = projectMap.get(tag);
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
			reportMap.put(project, report);
		}
		return reportMap;
	};
	
}
