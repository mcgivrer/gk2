/**
 * 
 */
package com.webcontext.apps.gk2.persist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 * @author frederic
 *
 */
public class DataLoader<T> {

	private static Logger logger = Logger.getLogger(DataLoader.class);
	/**
	 * 
	 */
	public DataLoader() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * <p>
	 * Load data from <code>filename</code> a yaml file for test purpose, and
	 * return a <code>List&lt;T&gt;</code> entity.
	 * </p>
	 * 
	 * @param filename
	 *            name of the YAML file containing data to load into the Entity
	 *            persistence table.
	 * @return
	@SuppressWarnings("unchecked")
	public Map<String, T> loadFromYaml(String filename, boolean storeList) {
		FileReader fileReader;
		Map<String, T> persistedEntities = null;
		if (storeList) {
			persistedEntities = new HashMap<String, T>();
		}
		try {
			fileReader = new FileReader(this.getClass().getResource("/")
					.getPath()
					+ filename + ".yaml");
			if (filename != null && !filename.equals("") && fileReader != null) {
				Yaml yaml = new Yaml();

				try {
					List<?> entities = (List<?>) yaml.load(fileReader);

					for (Object entity : entities) {
						LinkedHashMap<String, HashMap<String, Object>> lhm = (LinkedHashMap<String, HashMap<String, Object>>) entity;
						logger.debug(String.format("lhm=%s", lhm));
						ObjectMapper om = new ObjectMapper();
						String className = null;
						String id = null;
						for (String key : lhm.keySet()) {
							className = key.substring(0, key.indexOf("("));
							id = key.substring(key.indexOf("(") + 1,
									key.indexOf(")"));
							Object entityConverted = null;
							try {
								Class<?> entityClassToInstanciate = Class
										.forName(className);
								entityConverted = om.convertValue(lhm.get(key),
										entityClassToInstanciate);
								this.save((T) entityConverted);

								if (storeList) {
									persistedEntities.put(id,
											(T) entityConverted);
								}

								logger.debug(String.format(
										"Entity[%s]%s => %s", entity.getClass()
												.getSimpleName(), entity,
										entityConverted.toString()));

							} catch (ClassNotFoundException e) {
								logger.fatal(String.format(
										"Unknown class %s for entity %s",
										className, entityConverted), e);
							} catch (PersistenceException e) {
								logger.fatal(
										String.format(
												"Unable to save the entityConverted:%s",
												entityConverted), e);
							}

						}

					}
				} catch (PersistenceException te) {
					logger.fatal(String.format(
							"Error during loading yml file %s", filename), te);
				} finally {
					em.close();
				}
			}
		} catch (FileNotFoundException e) {
			list = null;
			logger.fatal(String.format(
					"Unable to read entities from '%s' Yaml file", filename));
		}
		return persistedEntities;

	}
	 */


}
