package com.icetea.manager.pagodiario.secured;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;

@Named
public class SecuredManager {
	
	private static final Logger LOGGER = getLogger(SecuredManager.class);
	
	private static final List<RoleSecured> SECURES = Lists.newArrayList();

	@PostConstruct
	public void init(){
		try {
			List<RoleSecured> list = this.buildSecures("com.despegar.ankaa.myo.controller");
			if(list != null){
				for(RoleSecured p : list){
					SECURES.add(p);
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public final List<RoleSecured> getSecures(){
		return SECURES;
	}
	
	public List<RoleSecured> buildSecures(String basePackage) throws IOException, ClassNotFoundException {
        
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        List<RoleSecured> candidates = Lists.newArrayList();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                                   resolveBasePackage(basePackage) + "/" + "**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
            	if (this.isCandidate(metadataReader, Controller.class)) {
            		List<RoleSecured> list = this.doGetSecures(metadataReader);
            		if(list != null && !list.isEmpty()){
            			candidates.addAll(list);
            		}
            	}
            }
        }
        
        return candidates;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    private boolean isCandidate(MetadataReader metadataReader, 
    		Class<? extends Annotation> annotationType) {
        try {
            Class<?> c = Class.forName(metadataReader.getClassMetadata().getClassName());
            if (c.getAnnotation(annotationType) != null) {
                return true;
            }
        }
        catch(Exception e){
        	
        }
        
        return false;
    }
    
    public static class RoleSecured {
    	private String path;
    	private String roleName;

    	public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
    	
    }
    
    private List<RoleSecured> doGetSecures(MetadataReader metadataReader) {
    	List<RoleSecured> secures = Lists.newArrayList();
        try {
            Class<?> c = Class.forName(metadataReader.getClassMetadata().getClassName());
            
            for (Method method : c.getMethods()) {
				CustomSecured secAnnotation = method.getAnnotation(CustomSecured.class);
				if(secAnnotation != null){
					RequestMapping rmAnnotation = method.getAnnotation(RequestMapping.class);
					if(rmAnnotation != null){
						String[] values = rmAnnotation.value();
						if(values != null && values.length > 0){
							String classUrl = "";
							// obtengo la url de la clase para componer el path completo ...
							RequestMapping classRqMappingAnnotation = c.getAnnotation(RequestMapping.class);
							if(classRqMappingAnnotation != null){
								if(classRqMappingAnnotation.value() != null && classRqMappingAnnotation.value().length > 0){
									classUrl = classRqMappingAnnotation.value()[0];
								}
							}
							for(String v : secAnnotation.value()){
								RoleSecured roleSecured = new RoleSecured();
								roleSecured.setRoleName(v);
								// [roher] asumo que siempre cargamos una url en el value ...
								roleSecured.setPath(classUrl + values[0]);
								
								secures.add(roleSecured);
							}
						}
					}
				}
			}
        }
        catch(Exception e){
        	
        }
        
        return secures;
    }
    
    public boolean exists(String path, String roleName){
    	for(RoleSecured p : SECURES){
    		if(StringUtils.endsWith(path, p.getPath()) 
    			&& StringUtils.equals(roleName, p.getRoleName())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public boolean hasToValidate(String path){
    	for(RoleSecured p : SECURES){
    		if(StringUtils.endsWith(path, p.getPath())){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
}
