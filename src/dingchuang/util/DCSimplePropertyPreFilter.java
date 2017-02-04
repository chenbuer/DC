package dingchuang.util;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

public class DCSimplePropertyPreFilter implements PropertyPreFilter {

	private final Class<?> clazz;
	private Set<String> includes = new HashSet<String>();
	private Set<String> excludes = new HashSet<String>();

	public DCSimplePropertyPreFilter(String... properties){  
        this(null, properties);  
    }

	public DCSimplePropertyPreFilter(Class<?> clazz, String... properties){  
        super();
        this.clazz = clazz;  
        for (String item : properties) {  
            if (item != null) {  
                this.includes.add(item);  
            }  
        }  
    }

	public Class<?> getClazz() {
		return clazz;
	}

	public Set<String> getIncludes() {
		return includes;
	}

	public Set<String> getExcludes() {
		return excludes;
	}

	public boolean apply(JSONSerializer serializer, Object source, String name) {
		if (source == null) {
			return true;
		}

		if (clazz != null && !clazz.isInstance(source)) {
			return true;
		}

		if (this.excludes.contains(name)) {
			return false;
		}

		if (includes.size() == 0 || includes.contains(name)) {
			return true;
		}

		return false;
	}

	public void setIncludes(Set<String> includes) {
		this.includes = includes;
	}

	public void setExcludes(Set<String> excludes) {
		this.excludes = excludes;
	}

}