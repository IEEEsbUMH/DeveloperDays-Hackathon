package com.ddieeeumh.actimel.ilibici.db;

import android.content.Context;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;
import com.mobandme.ada.exceptions.AdaFrameworkException;

public class AppDataContext extends ObjectContext{
	public ObjectSet<Usuario> userDao;
	public ObjectSet<Puestos> puestoDao;
	
	public AppDataContext(Context pContext) throws AdaFrameworkException {
		super(pContext, "ilibici.db", 1);
		userDao = new ObjectSet<Usuario>(Usuario.class, this);
		puestoDao = new ObjectSet<Puestos>(Puestos.class, this);
	}
}
