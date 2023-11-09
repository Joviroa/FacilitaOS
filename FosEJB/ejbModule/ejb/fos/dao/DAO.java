/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ejb.fos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public abstract class DAO {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	 public static EntityManager getEntityManager() {
	        if (em == null) {
	            emf = Persistence.createEntityManagerFactory("facilita_os");
	            em = emf.createEntityManager();
	        }
	        return em;
	 }

	public <T> T busca(Class<T> classe, Object id) {
		return getEntityManager().find(classe, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> buscaTodos(Class<T> classe) {
		return getEntityManager().createQuery("SELECT E FROM " + classe.getSimpleName() + " E").getResultList();
	}

	public <T> void recarrega(T entidade) {
		getEntityManager().refresh(entidade);
	}

	public <T> void persiste(T entidade) {
		getEntityManager().persist(entidade);
	}

	public <T> T atualiza(T entidade) {
		try {
			return getEntityManager().merge(entidade);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public <T> void remove(T entidade) {
		if (!getEntityManager().contains(entidade)) {
			entidade = getEntityManager().merge(entidade);
		}
		getEntityManager().remove(entidade);
	}
}
